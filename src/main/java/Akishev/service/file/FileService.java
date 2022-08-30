package Akishev.service.file;

import java.util.List;

public interface FileService {
    List<String> readFromFile(String filename);

    List<String> deleteFrequentWords(List<String> list, List<String> skippedWords);
}
