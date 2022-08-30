package Akishev.handler;

import Akishev.type.DataType;
import Akishev.type.Int;

public class IntTypeHandler implements TypeHandler {
    @Override
    public DataType createSketch(int width, int function, int k) {
        return Int.sketchOfIntType(width, function, k);
    }
}
