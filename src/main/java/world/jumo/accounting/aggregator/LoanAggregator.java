package world.jumo.accounting.aggregator;

import world.jumo.accounting.entity.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
public class LoanAggregator extends Aggregator<String, Transaction> {

    private static final char KEY_SEPARATOR = '|';
    private static final char COMMA = ',';
    private static final char SPACE = 32;
    private static final String DATE_FORMAT_KEY = "MM";
    private static final String DATE_FORMAT_DISPLAY = "MMMM";
    private static final SimpleDateFormat keyDateFormatter = new SimpleDateFormat(DATE_FORMAT_KEY);
    private static final SimpleDateFormat displayDateFormatter  = new SimpleDateFormat(DATE_FORMAT_DISPLAY);
    private static final String NEW_LINE = "\r\n";

    /**
     * Aggregate by Network, Product, Month
     *
     * @return key to aggregate the value with
     */
    @Override
    public String getKey(Transaction item) {

        if (item == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(item.getNetwork());
        sb.append(KEY_SEPARATOR);
        sb.append(item.getProduct());
        sb.append(KEY_SEPARATOR);
        sb.append(keyDateFormatter.format(item.getTransactionDate()));

        return sb.toString();
    }

    /**
     * Label by Network, Product, Month
     */
    @Override
    public List<String> getItemLabelList(Transaction item) {

        List<String> labelList = new ArrayList<>();

        labelList.add(item.getNetwork());
        labelList.add(item.getProduct());
        labelList.add(displayDateFormatter.format(item.getTransactionDate()));

        return labelList;
    }

    @Override
    public double getItemValue(Transaction item) {
        return item != null ? item.getAmount() : 0d;
    }

    @Override
    public String getHeading() {
        return "Network, Product, Month, Count, Amount, Average";
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Loan Summary");
        sb.append(NEW_LINE);
        sb.append("------------");
        sb.append(NEW_LINE);
        sb.append(getHeading());
        sb.append(NEW_LINE);

        for (AggregatorItemSummary summaryItem : super.getSummaryMap().values()) {

            for (String label : summaryItem.getItemLabelList()) {
                sb.append(label).append(COMMA).append(SPACE);
            }

            sb.append(summaryItem.getItemCount()).append(COMMA).append(SPACE);
            sb.append(summaryItem.getItemValue()).append(COMMA).append(SPACE);
            sb.append(summaryItem.getItemAverageValue()).append(COMMA).append(SPACE);
            sb.append(NEW_LINE);
        }

        sb.append(NEW_LINE);
        sb.append("TOTAL").append(COMMA).append(SPACE);
        sb.append(getItemCountTotal()).append(COMMA).append(SPACE);
        sb.append(getItemValueTotal()).append(COMMA).append(SPACE);
        sb.append(getItemAverageValueTotal());
        sb.append(NEW_LINE);

        return sb.toString();
    }

}
