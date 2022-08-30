package Akishev.handler;

import Akishev.type.Byte;
import Akishev.type.DataType;

public class ByteTypeHandler implements TypeHandler {
    @Override
    public DataType createSketch(int width, int function, int k) {
        return Byte.sketchOfByteType(width, function, k);
    }
}
