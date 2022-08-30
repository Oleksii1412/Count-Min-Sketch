package Akishev.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileReaderImpl implements FileReader {
    public List<String> readFromFile(String filename) {
        List<String> list = new ArrayList<>();
        try (InputStream in = getClass().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Optional
                     .ofNullable(in).orElseThrow(() ->
                             new RuntimeException("File path wrong or file doesn't exist! "
                             + "Path is: " + filename))))) {
            while (reader.ready()) {
                list.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return list;
    }
}
