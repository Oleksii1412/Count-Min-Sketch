package Akishev.writer;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileWriter {
    private static String OUTPUT_PATH;
    private static final String FORMAT = String.format("+%18s+%18s+%18s+%18s|\n", "-"
            .repeat(18), "-".repeat(18), "-".repeat(18), "-".repeat(18));

    public FileWriter(String path) {
        OUTPUT_PATH = path;
    }

    public void writeToFile(List<Map.Entry<String, Integer>> frequencyKTop,
                            Map<String, Long> words_Occurrence) {
        File file = new File(OUTPUT_PATH);
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new java.io.FileWriter(file))) {
            bufferedWriter.write(FORMAT);
            bufferedWriter.write(String.format("|%18s|%18s|%18s|%18s|\n",
                    StringUtils.center("word", 18),
                    StringUtils.center("freq_ref", 18),
                    StringUtils.center("freq_approx", 18),
                    StringUtils.center("error %", 18)));
            bufferedWriter.write(FORMAT);
            for (Map.Entry<String, Integer> k : frequencyKTop) {
                String word = k.getKey();
                long freqRef = words_Occurrence.get(word);
                long freqApprox = k.getValue();
                float error = (100.0f * Math.abs(freqApprox - freqRef)) / freqRef;
                bufferedWriter.write(String.format("|%18s|%18s|%18s|%18s|\n",
                        StringUtils.center(word, 18),
                        StringUtils.center(String.valueOf(freqRef), 18),
                        StringUtils.center(String.valueOf(freqApprox ),18),
                        StringUtils.center(String.valueOf(
                                Math.round(error * 100.0) / 100.0), 18)));
            }
            bufferedWriter.write(FORMAT);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + file, e);
        }
    }
}
