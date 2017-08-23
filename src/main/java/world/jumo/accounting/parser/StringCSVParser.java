package world.jumo.accounting.parser;

import world.jumo.accounting.aggregator.Aggregator;

import java.util.List;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
public class StringCSVParser extends CSVParser<List<String>> {

    public StringCSVParser(char defaultQuote, char defaultSeparator, Aggregator aggregator) {
        super(defaultQuote, defaultSeparator, aggregator);
    }

    public StringCSVParser(char defaultQuote, char defaultSeparator) {
        super(defaultQuote, defaultSeparator);
    }

    public StringCSVParser() {
        super();
    }

    public StringCSVParser(Aggregator aggregator) {
        super(aggregator);
    }

    @Override
    protected List<String> mapParsedLine(List<String> stringValues) {
        return stringValues;
    }

}
