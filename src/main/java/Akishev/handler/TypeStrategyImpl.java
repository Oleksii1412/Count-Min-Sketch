package Akishev.handler;

import Akishev.type.DataType;
import java.util.HashMap;
import java.util.Map;

public class TypeStrategyImpl implements TypeStrategy {
    private static final Map<DataType.Type,TypeHandler> TYPE_TYPE_HANDLER_MAP;

    static {
        TYPE_TYPE_HANDLER_MAP = new HashMap<>();
        TYPE_TYPE_HANDLER_MAP.put(DataType.Type.BYTE, new ByteTypeHandler());
        TYPE_TYPE_HANDLER_MAP.put(DataType.Type.SHORT, new ShortTypeHandler());
        TYPE_TYPE_HANDLER_MAP.put(DataType.Type.INT, new IntTypeHandler());
    }

    @Override
    public TypeHandler getTypeHandler(DataType.Type type) {
        return TYPE_TYPE_HANDLER_MAP.get(type);
    }
}
