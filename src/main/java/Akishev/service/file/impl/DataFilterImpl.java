package Akishev.service.file.impl;

import Akishev.service.file.DataFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilterImpl implements DataFilter {
    public List<String> deleteFrequentWords(List<String> list, List<String> skippedWords) {
        return list.stream()
                .map(s -> s.replaceAll("[^A-Za-z]", " "))
                .map(str -> str.split("\\s+"))
                .flatMap(Arrays::stream)
                .filter(str -> !skippedWords.contains(str.toLowerCase()) && !str.isEmpty())
                .collect(Collectors.toList());
    }
}
