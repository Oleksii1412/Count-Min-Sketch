package Akishev.service.file;

import java.util.List;

public interface DataFilter {
    List<String> deleteFrequentWords(List<String> list, List<String> skippedWords);
}
