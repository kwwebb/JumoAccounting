package world.jumo.accounting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import world.jumo.accounting.dto.TransactionDTO;
import world.jumo.accounting.entity.Transaction;

/**
 * Created by Kingsley Webb, (G981601) on 23/08/2017.
 */
@Mapper(uses = DateMapper.class)
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction transactionDtoToTransaction(TransactionDTO transactionDTO);

}
