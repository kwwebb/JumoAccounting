package world.jumo.accounting.aggregator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kingsley Webb on 23/08/2017.
 */
public abstract class Aggregator<K, T> {

    private Map<K, AggregatorItemSummary> summaryMap = new HashMap<>();
    private int itemCountTotal = 0;
    private double itemValueTotal = 0d;

    private AggregatorItemSummary getItemSummary(T item) {

        K key = getKey(item);
        AggregatorItemSummary summary = summaryMap.get(key);
        if (summary == null) {
            List<String> itemLabelList = getItemLabelList(item);
            summary = new AggregatorItemSummary(itemLabelList);
            summaryMap.put(key, summary);
        }
        return summary;
    }

    public abstract K getKey(T item);
    public abstract List<String> getItemLabelList(T item);
    public abstract double getItemValue(T item);
    public abstract String getHeading();

    public void process(T item) {

        AggregatorItemSummary summary = getItemSummary(item);
        double value = getItemValue(item);
        summary.appendItem(value);

        itemValueTotal += value;
        itemCountTotal++;
    }

    public void clear() {
        itemCountTotal = 0;
        itemValueTotal = 0d;
        summaryMap.clear();
    }

    public Map<K, AggregatorItemSummary> getSummaryMap() {
        return summaryMap;
    }

    public int getItemCountTotal() {
        return itemCountTotal;
    }

    public double getItemValueTotal() {
        return itemValueTotal;
    }

    public double getItemAverageValueTotal() {
        return itemCountTotal > 0 ? itemValueTotal / itemCountTotal : 0d;
    }

    @Override
    public String toString() {
        return "Aggregator{" +
                "summaryMap=" + summaryMap +
                ", itemCountTotal=" + itemCountTotal +
                ", itemValueTotal=" + itemValueTotal +
                '}';
    }
}
