package world.jumo.accounting.aggregator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import world.jumo.accounting.entity.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanAggregatorTest {

    LoanAggregator loanAggregator;

    @Before
    public void setup() {
        loanAggregator = new LoanAggregator();
    }

    @Test
    public void processTest() {

        Transaction t1 = new Transaction();
        t1.setAmount(111d);
        t1.setNetwork("N");
        t1.setProduct("P");
        t1.setTransactionDate(getJuneDate());

        Transaction t2 = new Transaction();
        t2.setAmount(222d);
        t2.setNetwork("N");
        t2.setProduct("P");
        t2.setTransactionDate(getAprilDate());

        Transaction t3 = new Transaction();
        t3.setAmount(333d);
        t3.setNetwork("N");
        t3.setProduct("P");
        t3.setTransactionDate(getJuneDate());

        Transaction t4 = new Transaction();
        t4.setAmount(444d);
        t4.setNetwork("N");
        t4.setProduct("P");
        t4.setTransactionDate(getAprilDate());

        Transaction t5 = new Transaction();
        t5.setAmount(555d);
        t5.setNetwork("N");
        t5.setProduct("P");
        t5.setTransactionDate(getJuneDate());

        loanAggregator.process(t1);
        loanAggregator.process(t2);
        loanAggregator.process(t3);
        loanAggregator.process(t4);
        loanAggregator.process(t5);

        System.out.println(loanAggregator.toString());

        System.out.println(loanAggregator.getSummaryMap());

        assertEquals(3, loanAggregator.getSummaryMap().get("N|P|06").getItemCount());
        assertEquals(999, loanAggregator.getSummaryMap().get("N|P|06").getItemValue(), 0.001);
        assertEquals(333, loanAggregator.getSummaryMap().get("N|P|06").getItemAverageValue(), 0.001);

        assertEquals(2, loanAggregator.getSummaryMap().get("N|P|04").getItemCount());
        assertEquals(666, loanAggregator.getSummaryMap().get("N|P|04").getItemValue(), 0.001);
        assertEquals(333, loanAggregator.getSummaryMap().get("N|P|04").getItemAverageValue(), 0.001);
    }

    private Date getAprilDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, new Random().nextInt(1000) * -1);
        c.set(Calendar.MONTH, Calendar.APRIL);

        return c.getTime();
    }

    private Date getJuneDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, new Random().nextInt(1000) * -1);
        c.set(Calendar.MONTH, Calendar.JUNE);

        return c.getTime();
    }

}
