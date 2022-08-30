package Akishev.handler;

import Akishev.type.DataType;

public interface TypeStrategy {
    TypeHandler getTypeHandler(DataType.Type type);
}
