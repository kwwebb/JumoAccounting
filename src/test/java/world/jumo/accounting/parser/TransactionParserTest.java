package world.jumo.accounting.parser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import world.jumo.accounting.entity.Transaction;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionParserTest {

    TransactionCSVParser parser;

    @Mock
    File file;

    @Before
    public void setup() {
        parser = new TransactionCSVParser('"', ';');
    }

    @Test
    public void parseLineTest() {

        Transaction txn = parser.mapParsedLine(parser.parseLine("27729234533;\"Network 2\";\"01-Apr-2016\";\"Loan Product 1\";5671.00"));

        assertNotNull(txn);
        assertEquals("Loan Product 1", txn.getProduct());
        assertEquals("Network 2", txn.getNetwork());
        assertEquals(5671d, txn.getAmount(), 0.001);
    }


}
