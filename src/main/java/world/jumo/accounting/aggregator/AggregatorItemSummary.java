package world.jumo.accounting.aggregator;

import java.util.List;

/**
 * Created by Kingsley Webb, (G981601) on 23/08/2017.
 */
public class AggregatorItemSummary {

    private int itemCount = 0;
    private double itemValue = 0d;
    private List<String> itemLabelList;

    public AggregatorItemSummary(List<String> itemLabelList) {
        super();
        this.itemLabelList = itemLabelList;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getItemValue() {
        return itemValue;
    }

    public List<String> getItemLabelList() {
        return itemLabelList;
    }

    public double getItemAverageValue() {
        return itemCount > 0 ? itemValue / itemCount : 0d;
    }

    public void appendItem(double itemValue) {
        this.itemCount++;
        this.itemValue += itemValue;
    }

    @Override
    public String toString() {
        return "AggregatorItemSummary{" +
                "itemCount=" + itemCount +
                ", itemValue=" + itemValue +
                '}';
    }

}
