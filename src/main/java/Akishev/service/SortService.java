package Akishev.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortService {
    public Map<String, Long> sortWords(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
