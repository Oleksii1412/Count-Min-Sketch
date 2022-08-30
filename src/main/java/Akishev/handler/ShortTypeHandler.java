package Akishev.handler;

import Akishev.type.DataType;
import Akishev.type.Short;

public class ShortTypeHandler implements TypeHandler {
    @Override
    public DataType createSketch(int width, int function, int k) {
        return Short.sketchOfShortType(width, function, k);
    }
}
