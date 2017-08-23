package world.jumo.accounting;

import world.jumo.accounting.aggregator.Aggregator;
import world.jumo.accounting.aggregator.AggregatorItemSummary;
import world.jumo.accounting.aggregator.LoanAggregator;
import world.jumo.accounting.entity.Transaction;
import world.jumo.accounting.parser.CSVParser;
import world.jumo.accounting.parser.TransactionCSVParser;
import world.jumo.accounting.util.CSVUtil;

import java.util.Iterator;
import java.util.List;

public class Main {

    private static boolean isYesOrTrue(String value) {
        return value != null && ("yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value));
    }

    public static void main(String[] args) throws Exception {

        if (args == null || args.length != 2) {
            System.out.println("Usage: java -jar jumo-accounting.jar <CSV File> <Skip Header true|false>");
            System.out.println();
            System.out.println("Help? Check out the README file.");
            return;
        }

        String csvFile = args[0];
        boolean skipHeader = isYesOrTrue(args[1]);

        // Create our aggregator
        Aggregator aggregator = new LoanAggregator();

        // Create our CSV parser; attach our aggregator to monitor transactions
        CSVParser csvParser = new TransactionCSVParser(aggregator);
        List<Transaction> transactions = csvParser.parseCsvFile(csvFile, skipHeader);

        // CSV
        CSVUtil csv = new CSVUtil();

        // CSV Header
        csv.appendHeader("Network/Product/Month");
        csv.appendHeader("Count");
        csv.appendHeader("Amount");
        csv.appendHeader("Average");

        // CSV Body
        Iterator<AggregatorItemSummary> summaryIterator = aggregator.getSummaryMap().values().iterator();
        while (summaryIterator.hasNext()) {

            AggregatorItemSummary summaryItem = summaryIterator.next();

            StringBuilder labelBuilder = new StringBuilder();

            for (String label : summaryItem.getItemLabelList()) {
                if (label.length() > 0) {
                    labelBuilder.append("/");
                }
                labelBuilder.append(label);
            }
            csv.append(labelBuilder.toString());
            csv.append(summaryItem.getItemCount(), "#0");
            csv.append(summaryItem.getItemValue());
            csv.append(summaryItem.getItemAverageValue());
        }

        // CSV Summary Totals
        csv.append("TOTAL");
        csv.append(aggregator.getItemCountTotal(), "#0");
        csv.append(aggregator.getItemValueTotal());
        csv.append(aggregator.getItemAverageValueTotal());

        // Write CSV File
        boolean includeHeader = true;
        csv.toCSVFile("Output.csv", includeHeader);
    }

}
