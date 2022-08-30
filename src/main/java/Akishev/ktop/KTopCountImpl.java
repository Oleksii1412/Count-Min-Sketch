package Akishev.ktop;

import java.util.Comparator;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KTopCountImpl implements KTopCount {
    private final Map<String, Integer> numberOfOccurrences = new HashMap<>();
    private final int k;

    public KTopCountImpl(int k) {
        this.k = k;
    }

    @Override
    public void add(String key, int frequency) {
        if (numberOfOccurrences.get(key) != null) {
            numberOfOccurrences.replace(key, frequency);
        } else {
            if (numberOfOccurrences.size() < k) {
                numberOfOccurrences.put(key, frequency);
            } else {
                String lowest_Frequency = Collections.min(numberOfOccurrences.entrySet(),
                        Map.Entry.comparingByValue()).getKey();
                if (frequency > numberOfOccurrences.get(lowest_Frequency)) {
                    numberOfOccurrences.remove(lowest_Frequency);
                    numberOfOccurrences.put(key, frequency);
                }
            }
        }
    }

    @Override
    public Map<String, Integer> getKTop() {
        return numberOfOccurrences;
    }

    @Override
    public List<Map.Entry<String, Integer>> parseMapKTopToList(KTopCountImpl kTopCountImpl) {
        return kTopCountImpl.getKTop().entrySet().stream().sorted(Map.Entry
                .comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
    }
}
