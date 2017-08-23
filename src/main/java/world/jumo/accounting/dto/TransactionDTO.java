package world.jumo.accounting.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kingsley Webb, (G981601) on 23/08/2017.
 *
 * Read-only Transaction DTO; make writable if required for reverse mapping
 */
public class TransactionDTO {

    private List<String> transactionValues;

    public TransactionDTO() {
        super();
        this.transactionValues = new ArrayList<>();
    }

    public TransactionDTO(List<String> values) {
        this();
        this.transactionValues = values;
    }

    public String getTransactionId() {
        return transactionValues.size() > 0 ? transactionValues.get(0) : null;  // At index 0
    }

    public String getNetwork() {
        return transactionValues.size() > 1 ? transactionValues.get(1) : null;  // At index 1
    }

    public String getTransactionDate() {
        return transactionValues.size() > 2 ? transactionValues.get(2) : null;  // At index 2
    }

    public String getProduct() {
        return transactionValues.size() > 3 ? transactionValues.get(3) : null;  // At index 3
    }

    public String getAmount() {
        return transactionValues.size() > 4 ? transactionValues.get(4) : null;  // At index 4
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "transactionId='" + getTransactionId() + '\'' +
                ", network='" + getNetwork() + '\'' +
                ", transactionDate='" + getTransactionDate() + '\'' +
                ", product='" + getProduct() + '\'' +
                ", amount='" + getAmount() + '\'' +
                '}';
    }

}
