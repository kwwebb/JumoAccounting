package world.jumo.accounting;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args == null || args.length != 1) {
            System.out.println("Usage: java -jar jumo-accounting.jar <CSV File>");
            System.out.println();
            System.out.println("Help? Check out the README file.");
            return;
        }

        String csvFile = args[0];

        CSVParser<Transaction> csvParser = new CSVParser<>();
        System.out.println(csvParser.parseCsvFile(csvFile));

    }

}
