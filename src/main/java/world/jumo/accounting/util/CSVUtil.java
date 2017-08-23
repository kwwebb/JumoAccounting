package world.jumo.accounting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
public class CSVUtil {

    private static final Logger log = LoggerFactory.getLogger(CSVUtil.class);

    private static final String NEW_LINE = "\r\n";

    private static final char DEFAULT_QUOTE = '\'';
    private static final char DEFAULT_SEPARATOR = ',';
    private static final String DEFAULT_NUMBER_FORMAT = "#0.00";

    private char defaultQuote;
    private char defaultSeparator;

    private StringBuilder csvHeader;
    private StringBuilder csvBody;

    private int csvHeaderFieldCount;
    private int csvBodyFieldCount;

    public CSVUtil(char defaultQuote, char defaultSeparator) {
        super();

        clear();  // Initialize composition

        this.defaultQuote = defaultQuote;
        this.defaultSeparator = defaultSeparator;
    }

    public CSVUtil() {
        this(DEFAULT_QUOTE, DEFAULT_SEPARATOR);
    }

    public CSVUtil appendHeader(String string) {

        if (csvHeader.length() > 0) {
            csvHeader.append(defaultSeparator);
        }

        csvHeader.append(string);
        csvHeaderFieldCount++;

        return this;
    }

    private void addSeperator() {
        if (csvBodyFieldCount > 0) {
            csvBody.append(defaultSeparator);
        }
    }

    private void addNextLine() {
        if (csvBodyFieldCount >= csvHeaderFieldCount) {
            csvBody.append(NEW_LINE);
            csvBodyFieldCount = 0;
        }
    }

    public CSVUtil append(String string) {

        addSeperator();

        csvBody.append(defaultQuote);
        csvBody.append(string);
        csvBody.append(defaultQuote);

        csvBodyFieldCount++;
        addNextLine();

        return this;
    }

    public CSVUtil append(double number, String formatPattern) {

        addSeperator();

        NumberFormat numberFormatter = new DecimalFormat(formatPattern);
        csvBody.append(numberFormatter.format(number));

        csvBodyFieldCount++;
        addNextLine();

        return this;
    }

    public CSVUtil append(double number) {
        return append(number, DEFAULT_NUMBER_FORMAT);
    }

    public CSVUtil append(Date date, String formatPattern) {

        addSeperator();

        csvBody.append(defaultQuote);

        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        csvBody.append(sdf.format(date));

        csvBody.append(defaultQuote);

        csvBodyFieldCount++;
        addNextLine();

        return this;
    }

    public void clear() {
        csvHeader = new StringBuilder();
        csvBody = new StringBuilder();

        csvHeaderFieldCount = 0;
        csvBodyFieldCount = 0;
    }

    public String toCSV(boolean includeHeader) {

        StringBuilder csv = new StringBuilder();
        if (includeHeader) {
            csv.append(csvHeader);
            csv.append(NEW_LINE);
        }

        csv.append(csvBody);
        csv.append(NEW_LINE);

        return csv.toString();
    }

    public void toCSVFile(String fileName, boolean includeHeader) throws IOException {
        toCSVFile(new File(fileName), includeHeader);
    }

    public void toCSVFile(File file, boolean includeHeader) throws IOException {

        log.info("Writing CSV File: " + file.getCanonicalFile().toString());

        FileWriter fileWriter = new FileWriter(file);
        try {
            fileWriter.append(toCSV(includeHeader));
        }
        finally {
            fileWriter.flush();
            fileWriter.close();
        }
    }

}
