package Akishev.type;

import Akishev.hash.HashFunction;

public class Int extends DataType {
    private static final int NUMBER_OF_BITS_PER_COUNTER = 32;
    private final int [][] intTable;
    private final HashFunction hashFunction;

    private Int(int width, int function, int k) {
        super(width, function, k);
        this.intTable = new int[function][width];
        this.hashFunction = new HashFunction(NUMBER_OF_BITS_PER_COUNTER, width);
    }

    public static Int sketchOfIntType(int width, int function, int k) {
        return new Int(width, function, k);
    }

   @Override
    public void insertToSketch(String element) {
       for (int i = 0; i < getFunction(); i++) {
           int hash = hashFunction.getBucketPosition(element.getBytes(), i);
           if (intTable[i][hash] != -1)
               intTable[i][hash]++;
       }
    }

    @Override
    public void countMin(String element) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < getFunction(); i++) {
            int pos = hashFunction.getBucketPosition(element.getBytes(), i);
            if (intTable[i][pos] < 0) {
                int number = intTable[i][pos] & 0xff;
                min = Math.min(min, number);
            } else {
                min = Math.min(min, intTable[i][pos]);
            }
        }
        getkTop().add(element, min);
    }
}
