package Akishev.type;

public interface CountMinSketch {
    void insertToSketch(String element);

    void countMin(String element);
}
