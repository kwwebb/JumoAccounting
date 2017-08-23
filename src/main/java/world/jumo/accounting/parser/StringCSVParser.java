package world.jumo.accounting.parser;

import java.util.List;

/**
 * Created by Kingsley Webb, (G981601) on 23/08/2017.
 */
public class StringCSVParser extends CSVParser<List<String>> {

    @Override
    protected List<String> mapParsedLine(List<String> stringValues) {
        return stringValues;
    }

}
