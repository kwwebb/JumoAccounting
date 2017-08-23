package world.jumo.accounting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kingsley Webb, (G981601) on 21/08/2017.
 */
public class CSVParser<T> {

    private static final char DEFAULT_QUOTE = '\'';
    private static final char DEFAULT_SEPARATOR = ',';

    private char defaultQuote;
    private char defaultSeparator;

    public CSVParser(char defaultQuote, char defaultSeparator) {
        super();
        this.defaultQuote = defaultQuote;
        this.defaultSeparator = defaultSeparator;
    }

    public CSVParser() {
        this(DEFAULT_QUOTE, DEFAULT_SEPARATOR);
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

        for (char character : lineCharacters) {
            switch (character) {
                default: stringBuilder.append(character);
            }

            if (stringBuilder.length() > 5) {
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }

        return result;
    }

    public List<List<String>> parseCsvFile(String csvFile) throws Exception {
        return parseCsvFile(new File(csvFile));
    }

    public List<List<String>> parseCsvFile(File csvFile) throws Exception {

        List<List<String>> matrix = new ArrayList<>();
        Scanner scanner = new Scanner(csvFile);

        while (scanner.hasNext()) {
            List<String> parsedLine = parseLine(scanner.nextLine());
            matrix.add(parsedLine);
        }

        return matrix;
    }

}