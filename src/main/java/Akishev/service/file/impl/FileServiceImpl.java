package Akishev.service.file.impl;

import Akishev.service.file.DataFilter;
import Akishev.reader.FileReader;
import Akishev.reader.FileReaderImpl;
import Akishev.service.file.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {
    private final FileReader READER;
    private final DataFilter FILTER;

    public FileServiceImpl () {
        READER = new FileReaderImpl();
        FILTER = new DataFilterImpl();
    }

    @Override
    public List<String> readFromFile(String filename) {
        return READER.readFromFile(filename);
    }

    @Override
    public List<String> deleteFrequentWords(List<String> list, List<String> skippedWords) {
        return FILTER.deleteFrequentWords(list, skippedWords);
    }
}
