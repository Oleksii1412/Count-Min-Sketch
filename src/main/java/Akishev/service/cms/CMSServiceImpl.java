package Akishev.service.cms;

import Akishev.handler.TypeStrategy;
import Akishev.type.DataType;
import java.util.List;

public class CMSServiceImpl implements CMSService {
    private final TypeStrategy typeStrategy;

    public CMSServiceImpl(TypeStrategy typeStrategy) {
        this.typeStrategy = typeStrategy;
    }

    public DataType buildSketch(String type, int width, int function, int k) {
        return typeStrategy.getTypeHandler(DataType
                .Type.valueOf(type.toUpperCase())).createSketch(width, function, k);
    }

    public void insertToSketch(List<String> text, DataType sketch) {
        text.forEach(sketch::insertToSketch);
    }

    public void countMinSketch(List<String> text, DataType sketch) {
        text.forEach(sketch::countMin);
    }
}
