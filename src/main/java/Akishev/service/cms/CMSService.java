package Akishev.service.cms;

import Akishev.type.DataType;

import java.util.List;

public interface CMSService {
    DataType buildSketch(String type, int width, int function, int k);

    void insertToSketch(List<String> text, DataType sketch);

    void countMinSketch(List<String> text, DataType sketch);
}
