package Akishev.changeover;

import Akishev.type.DataType;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manual extends Mode {
    private static final String DEFAULT_INPUT_PATH = "/input_file.txt";
    private static final String DEFAULT_SKIP_WORDS_PATH = "/skip_words.txt";
    private static final int MAXIMUM_INPUT_VALUE = 5000;
    private static Scanner scanner;

    public static class Builder {
        private String DATA_TYPE;
        private String INPUT_PATH;
        private String SKIP_WORDS_PATH;
        private int KTop_FREQUENCY;
        private int HASH_FUNCTION_AMOUNT;
        private int SKETCH_BUFFER_SIZE;

        public void setDATA_TYPE(String DATA_TYPE) {
            this.DATA_TYPE = DATA_TYPE;
        }

        public void setINPUT_PATH(String INPUT_PATH) {
            this.INPUT_PATH = INPUT_PATH;
        }

        public void setSKIP_WORDS_PATH(String SKIP_WORDS_PATH) {
            this.SKIP_WORDS_PATH = SKIP_WORDS_PATH;
        }

        public void setKTop_FREQUENCY(int KTop_FREQUENCY) {
            this.KTop_FREQUENCY = KTop_FREQUENCY;
        }

        public void setHASH_FUNCTION_AMOUNT(int HASH_FUNCTION_AMOUNT) {
            this.HASH_FUNCTION_AMOUNT = HASH_FUNCTION_AMOUNT;
        }

        public void setSKETCH_BUFFER_SIZE(int SKETCH_BUFFER_SIZE) {
            this.SKETCH_BUFFER_SIZE = SKETCH_BUFFER_SIZE;
        }

        public Manual build() {
            return new Manual(this);
        }
    }

    public static Manual startManualMode() {
        scanner = new Scanner(System.in);
        return setInputVariable().build();
    }

    private Manual(Builder builder) {
        super(builder.DATA_TYPE, builder.INPUT_PATH, builder.SKIP_WORDS_PATH,
                builder.KTop_FREQUENCY, builder.HASH_FUNCTION_AMOUNT, builder.SKETCH_BUFFER_SIZE);
    }

    private static Builder setInputVariable() {
        Builder builder = new Builder();
        System.out.println("\nPlease choose what you want to use as an input file: ");
        System.out.println("- Press 1, then enter the path of your personnel file.");
        System.out.println("- Press 2, use a default application file.");
        builder.setINPUT_PATH(inputFile());
        System.out.println("\nPlease choose what you want to use as a skip words file: ");
        System.out.println("- Press 1, then enter the path of your personnel file.");
        System.out.println("- Press 2, use a default application file.");
        builder.setSKIP_WORDS_PATH(skipWordsFileInput());
        System.out.println("\nPlease enter a number of top frequent elements that we are looking for: ");
        builder.setKTop_FREQUENCY(inputSize());
        System.out.println("\nPlease enter a number of the Count Min Sketch buffer size: ");
        builder.setSKETCH_BUFFER_SIZE(inputSize());
        System.out.println("\nPlease enter a number of independent hash functions: ");
        builder.setHASH_FUNCTION_AMOUNT(inputSize());
        System.out.println("\nPlease choose a number of bits per counter: ");
        System.out.println("- Press 1, to choose byte type with 8 bits.");
        System.out.println("- Press 2, to choose short type with 16 bits.");
        System.out.println("- Press 3, to choose int type with 32 bits.");
        builder.setDATA_TYPE(bitsPerCounter());
        return builder;
    }

    private static String inputFile() {
        while (true) {
            System.out.print("Enter number here: ");
            try {
                int input = scanner.nextInt();
                if (input == 1) {
                    System.out.print("Enter, as an e.g. '/input.txt' your path here: ");
                    scanner = new Scanner(System.in);
                    return scanner.nextLine();
                } else if (input == 2) {
                    return DEFAULT_INPUT_PATH;
                } else {
                    System.out.println("You didn't choose any variant! Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please, enter a string path!");
                scanner = new Scanner(System.in);
            }
        }
    }

    private static String skipWordsFileInput() {
        while (true) {
            System.out.print("Enter number here: ");
            try {
                int input = scanner.nextInt();
                if (input == 1) {
                    System.out.print("Enter, as an e.g. '/skip.txt' your path here: ");
                    scanner = new Scanner(System.in);
                    return scanner.nextLine();
                } else if (input == 2) {
                    return DEFAULT_SKIP_WORDS_PATH;
                } else {
                    System.out.println("You didn't choose any variant! Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please, enter a string path!");
                scanner = new Scanner(System.in);
            }
        }
    }

    private static int inputSize() {
        while (true) {
            System.out.print("Enter number here: ");
            try {
                int input = scanner.nextInt();
                if (input < MAXIMUM_INPUT_VALUE) {
                    return input;
                } else {
                    System.out.println("Your number is too high! Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please, enter a number!");
                scanner = new Scanner(System.in);
            }
        }
    }

    private static String bitsPerCounter() {
        while (true) {
            System.out.print("Enter number here: ");
            try {
                int input = scanner.nextInt();
                if (input == 1) {
                    return DataType.Type.BYTE.getType();
                } else if (input == 2) {
                    return DataType.Type.SHORT.getType();
                } else if (input == 3) {
                    return DataType.Type.INT.getType();
                } else {
                    System.out.println("You didn't choose any variant! Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please, enter a number!");
                scanner = new Scanner(System.in);
            }
        }
    }
}
