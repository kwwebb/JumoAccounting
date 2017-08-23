package world.jumo.accounting.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import world.jumo.accounting.dto.TransactionDTO;
import world.jumo.accounting.entity.Transaction;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kingsley Webb, (G981601) on 23/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionMapperTest {

    @Test
    public void transactionDTOtoTransactionTest() {

        // given
        TransactionDTO transactionDTO = getTransactionDTO();

        // when
        Transaction transaction = TransactionMapper.INSTANCE.transactionDtoToTransaction(transactionDTO);

        // then
        assertNotNull(transaction);
        assertEquals(27729554427L, transaction.getTransactionId());
        assertEquals("Network 1", transaction.getNetwork());
        assertEquals(DateMapperTest.getDate(), transaction.getTransactionDate());
        assertEquals("Loan Product 1", transaction.getProduct());
        assertEquals(1000d, transaction.getAmount(), 0.001);
    }

    private TransactionDTO getTransactionDTO() {

        String[] transaction = {"27729554427", "Network 1", "12-Mar-2016", "Loan Product 1", "1000.00"};
        List<String> stringList = Arrays.asList(transaction);
        return new TransactionDTO(stringList);
    }

}
