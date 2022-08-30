package Akishev.type;

import Akishev.ktop.KTopCountImpl;

public abstract class DataType implements CountMinSketch {
    private KTopCountImpl kTopCountImpl;
    private int width;
    private int function;

    public DataType(int width, int function, int k) {
        this.width = width;
        this.function = function;
        this.kTopCountImpl = new KTopCountImpl(k);
    }

    public KTopCountImpl getkTop() {
        return kTopCountImpl;
    }

    public void setkTop(KTopCountImpl kTopCountImpl) {
        this.kTopCountImpl = kTopCountImpl;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public enum Type {
        BYTE("byte"),
        SHORT("short"),
        INT("int");
        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
