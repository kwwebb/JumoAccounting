package world.jumo.accounting.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.jumo.accounting.aggregator.Aggregator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kingsley Webb on 21/08/2017.
 */
public abstract class CSVParser<T> {

    private static final Logger log = LoggerFactory.getLogger(CSVParser.class);

    private static final char DEFAULT_QUOTE = '\'';
    private static final char DEFAULT_SEPARATOR = ',';

    private char defaultQuote;
    private char defaultSeparator;
    private Aggregator aggregator;

    public CSVParser(char defaultQuote, char defaultSeparator, Aggregator aggregator) {
        super();
        this.defaultQuote = defaultQuote;
        this.defaultSeparator = defaultSeparator;
        this.aggregator = aggregator;
    }

    public CSVParser(char defaultQuote, char defaultSeparator) {
        this(defaultQuote, defaultSeparator, null);
    }

    public CSVParser() {
        this(DEFAULT_QUOTE, DEFAULT_SEPARATOR, null);
    }

    public CSVParser(Aggregator aggregator) {
        this(DEFAULT_QUOTE, DEFAULT_SEPARATOR, aggregator);
    }

    public List<String> parseLine(String csvLine) {

        // Check for null input
        if (csvLine == null) {
            return null;
        }

        List<String> result = new ArrayList<>();

        // Check for empty input; return immediately
        if (csvLine.isEmpty()) {
            return result;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] lineCharacters = csvLine.toCharArray();
        boolean openQuote = false;

        for (char character : lineCharacters) {

            // Separator - capture field, unless within quotes
            if (character == defaultSeparator && !openQuote) {
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                continue;
            }

            // Quotes - manage open/closing quotes
            if (character == defaultQuote) {
                openQuote = !openQuote;
                continue;
            }

            // Skip constant characters
            switch (character) {
                case '\r': break;  // Skip carriage return
                case '\n':
                    flushBuffer(result, stringBuilder);
                    openQuote = false;
                    break;  // Skip line feed
                default: stringBuilder.append(character);
            }
        }

        // Empty the buffer if has a last field
        flushBuffer(result, stringBuilder);

        return result;
    }

    private void flushBuffer(List<String> result, StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            result.add(stringBuilder.toString());
        }
    }

    public List<T> parseCsvFile(String csvFile, boolean skipHeader) throws Exception {
        File file = new File(csvFile);

        if (file.exists()) {
            log.info("Processing file: " + file.getCanonicalFile().toString());
        }
        return parseCsvFile(new File(csvFile), skipHeader);
    }

    protected abstract T mapParsedLine(List<String> stringValues);

    public List<T> parseCsvFile(File csvFile, boolean skipHeader) throws Exception {

        List<T> csvParsedLines = new ArrayList<>();
        Scanner scanner = new Scanner(csvFile);

        if (skipHeader && scanner.hasNext()) {
            scanner.nextLine(); // Skip the first line (of headers)
        }

        while (scanner.hasNext()) {
            List<String> parsedLine = parseLine(scanner.nextLine());
            T parsedObject = mapParsedLine(parsedLine);

            if (aggregator != null) {
                aggregator.process(parsedObject);
            }

            csvParsedLines.add(parsedObject);
        }

        return csvParsedLines;
    }

}