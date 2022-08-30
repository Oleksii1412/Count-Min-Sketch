package Akishev.service;

import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;

public class PrintService {
    private static final String FORMAT = String.format("+%18s+%18s+%18s+%18s|\n",
            "-".repeat(18), "-".repeat(18),
            "-".repeat(18), "-".repeat(18));

    public void printOutputTable(List<Map.Entry<String, Integer>> frequencyKTop,
                                 Map<String, Long> words_Occurrence) {
        System.out.println(FORMAT);
        System.out.printf("|%18s|%18s|%18s|%18s|\n%n",
                StringUtils.center("word", 18), StringUtils.center("freq_ref", 18),
                StringUtils.center("freq_approx", 18),
                StringUtils.center("error %", 18));
        System.out.println(FORMAT);
        for (Map.Entry<String, Integer> k : frequencyKTop) {
            String word = k.getKey();
            long freqRef = words_Occurrence.get(word);
            long freqApprox = k.getValue();
            float error = (100.0f * Math.abs(freqApprox - freqRef)) / freqRef;
            System.out.printf("|%18s|%18s|%18s|%18s|\n%n",
                    StringUtils.center(word, 18),
                    StringUtils.center(String.valueOf(freqRef), 18),
                    StringUtils.center(String.valueOf(freqApprox), 18),
                    StringUtils.center(String.valueOf(
                            Math.round(error * 100.0) / 100.0), 18));
        }
        System.out.println(FORMAT);
    }
}
