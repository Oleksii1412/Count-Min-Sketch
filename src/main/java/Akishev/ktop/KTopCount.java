package Akishev.ktop;

import java.util.List;
import java.util.Map;

public interface KTopCount {
    void add(String key, int frequency);

    Map<String, Integer> getKTop();

    List<Map.Entry<String, Integer>> parseMapKTopToList(KTopCountImpl kTopCountImpl);
}
