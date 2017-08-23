package world.jumo.accounting;

import java.util.Date;

/**
 * Created by Kingsley Webb, (G981601) on 22/08/2017.
 */
public class Transaction {

    private long transactionId;
    private String network;
    private Date transactionDate;
    private String product;
    private double amount;

    public Transaction() {
        super();
    }

    public Transaction(long transactionId, String network, Date transactionDate, String product, double amount) {
        this();
        this.transactionId = transactionId;
        this.network = network;
        this.transactionDate = transactionDate;
        this.product = product;
        this.amount = amount;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (transactionId != that.transactionId) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (network != null ? !network.equals(that.network) : that.network != null) return false;
        if (transactionDate != null ? !transactionDate.equals(that.transactionDate) : that.transactionDate != null)
            return false;
        return !(product != null ? !product.equals(that.product) : that.product != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (transactionId ^ (transactionId >>> 32));
        result = 31 * result + (network != null ? network.hashCode() : 0);
        result = 31 * result + (transactionDate != null ? transactionDate.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", network='" + network + '\'' +
                ", transactionDate=" + transactionDate +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                '}';
    }

}
