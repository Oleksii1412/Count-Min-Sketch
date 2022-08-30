package Akishev.type;

import Akishev.hash.HashFunction;

public class Short extends DataType {
    private static final int NUMBER_OF_BITS_PER_COUNTER = 16;
    private final short [][] shortTable;
    private final HashFunction hashFunction;

    private Short(int width, int function, int k) {
        super(width, function, k);
        this.shortTable = new short[function][width];
        this.hashFunction = new HashFunction(NUMBER_OF_BITS_PER_COUNTER, width);
    }

    public static Short sketchOfShortType(int width, int function, int k) {
        return new Short(width, function, k);
    }

    @Override
    public void insertToSketch(String element) {
        for (int i = 0; i < getFunction(); i++) {
            int hash = hashFunction.getBucketPosition(element.getBytes(), i);
            if (shortTable[i][hash] != -1)
                shortTable[i][hash]++;
        }
    }

    @Override
    public void countMin(String element) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < getFunction(); i++) {
            int pos = hashFunction.getBucketPosition(element.getBytes(), i);
            if (shortTable[i][pos] < 0) {
                int number = shortTable[i][pos] & 0xff;
                min = Math.min(min, number);
            } else {
                min = Math.min(min, shortTable[i][pos]);
            }
        }
        getkTop().add(element, min);
    }
}
