package world.jumo.accounting.parser;

import world.jumo.accounting.aggregator.Aggregator;
import world.jumo.accounting.dto.TransactionDTO;
import world.jumo.accounting.entity.Transaction;
import world.jumo.accounting.mapper.TransactionMapper;

import java.util.List;

/**
 * Created by Kingsley Webb, (G981601) on 23/08/2017.
 */
public class TransactionCSVParser extends CSVParser<Transaction> {

    public TransactionCSVParser(char defaultQuote, char defaultSeparator, Aggregator aggregator) {
        super(defaultQuote, defaultSeparator, aggregator);
    }

    public TransactionCSVParser(char defaultQuote, char defaultSeparator) {
        super(defaultQuote, defaultSeparator);
    }

    public TransactionCSVParser() {
        super();
    }

    public TransactionCSVParser(Aggregator aggregator) {
        super(aggregator);
    }

    @Override
    protected Transaction mapParsedLine(List stringValues) {

        // TransactionDTO(List<String>) -> TransactionDTO
        TransactionDTO transactionDTO = new TransactionDTO(stringValues);
        Transaction transaction = TransactionMapper.INSTANCE.transactionDtoToTransaction(transactionDTO);

        return transaction;
    }
}
