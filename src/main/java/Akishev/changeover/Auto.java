package Akishev.changeover;

public class Auto extends Mode {
    private static final float DEFAULT_EPSILON = 0.005f;
    private static final float DEFAULT_DELTA = 0.02f;
    private static final String DATA_TYPE = "int";
    private static final String INPUT_PATH = "/input_file.txt";
    private static final String SKIP_WORDS_PATH = "/skip_words.txt";
    private static final int KTop_FREQUENCY = 10;
    private static final int HASH_FUNCTION_AMOUNT = (int) Math.ceil(Math.log(1 / DEFAULT_DELTA));
    private static final int SKETCH_BUFFER_SIZE = (int) (Math.exp(1.0) / DEFAULT_EPSILON);

    private Auto() {
        super(DATA_TYPE, INPUT_PATH, SKIP_WORDS_PATH,
                KTop_FREQUENCY, HASH_FUNCTION_AMOUNT,SKETCH_BUFFER_SIZE);
    }

    public static Auto startAutoMode() {
        return new Auto();
    }
}
