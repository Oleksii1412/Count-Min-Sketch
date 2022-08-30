package Akishev.type;

import Akishev.hash.HashFunction;

public class Byte extends DataType {
    private static final int NUMBER_OF_BITS_PER_COUNTER = 8;
    private final byte [][] byteTable;
    private final HashFunction hashFunction;

    private Byte(int width, int function, int k) {
        super(width, function, k);
        this.byteTable = new byte[function][width];
        this.hashFunction = new HashFunction(NUMBER_OF_BITS_PER_COUNTER, width);
    }

    public static Byte sketchOfByteType(int width, int function, int k) {
        return new Byte(width, function, k);
    }

    @Override
    public void insertToSketch(String element) {
        for (int i = 0; i < getFunction(); i++) {
            int hash = hashFunction.getBucketPosition(element.getBytes(), i);
            if (byteTable[i][hash] != -1)
                byteTable[i][hash]++;
        }
    }

    @Override
    public void countMin(String element) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < getFunction(); i++) {
            int pos = hashFunction.getBucketPosition(element.getBytes(), i);
            if (byteTable[i][pos] < 0) {
                int number = byteTable[i][pos] & 0xff;
                min = Math.min(min, number);
            } else {
                min = Math.min(min, byteTable[i][pos]);
            }
        }
            getkTop().add(element, min);
    }
}
