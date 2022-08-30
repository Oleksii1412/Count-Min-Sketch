package Akishev.hash;

public class HashFunction {
    private final int numberOfBitsPerCounter;
    private final int width;

    public HashFunction(int number, int width) {
        this.numberOfBitsPerCounter = number;
        this.width = width;
    }

    public int getBucketPosition(byte[] key, int i) {
        long hash64 = Murmur3.hash64(key);
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> numberOfBitsPerCounter);
        int combinedHash = hash1 + (i * hash2);
        if (combinedHash < 0) {
            combinedHash = ~combinedHash;
        }
        return combinedHash % width;
    }
}
