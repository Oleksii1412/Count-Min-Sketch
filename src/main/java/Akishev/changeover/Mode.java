package Akishev.changeover;

import Akishev.handler.TypeStrategy;
import Akishev.handler.TypeStrategyImpl;
import Akishev.service.cms.CMSService;
import Akishev.service.cms.CMSServiceImpl;
import Akishev.service.file.FileService;
import Akishev.service.file.impl.FileServiceImpl;
import Akishev.service.PrintService;
import Akishev.service.SortService;
import Akishev.type.DataType;
import Akishev.writer.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.InputMismatchException;

public abstract class Mode {
    private final String DATA_TYPE;
    private final String INPUT_PATH;
    private final String SKIP_WORDS_PATH;
    private final int KTop_FREQUENCY;
    private final int HASH_FUNCTION_AMOUNT;
    private final int SKETCH_BUFFER_SIZE;
    private final FileService FILE_SERVICE;
    private final PrintService PRINT_SERVICE;
    private final SortService SORT_SERVICE;

    public Mode(String dataType, String inputPath, String skipWords,
                int kTopFrequency, int hashFuncAmount, int bufferSizeSketch) {
        this.DATA_TYPE = dataType;
        this.INPUT_PATH = inputPath;
        this.SKIP_WORDS_PATH = skipWords;
        this.KTop_FREQUENCY = kTopFrequency;
        this.HASH_FUNCTION_AMOUNT = hashFuncAmount;
        this.SKETCH_BUFFER_SIZE = bufferSizeSketch;
        this.FILE_SERVICE = new FileServiceImpl();
        this.PRINT_SERVICE = new PrintService();
        this.SORT_SERVICE = new SortService();
    }

    public void startMode() {
        List<String> input = FILE_SERVICE.readFromFile(INPUT_PATH);
        List<String> skipWords = FILE_SERVICE.readFromFile(SKIP_WORDS_PATH);
        List<String> cleanList = FILE_SERVICE.deleteFrequentWords(input, skipWords);
        TypeStrategy typeStrategy = new TypeStrategyImpl();
        CMSService cmsService = new CMSServiceImpl(typeStrategy);
        DataType sketch = cmsService.buildSketch(DATA_TYPE, SKETCH_BUFFER_SIZE,
                HASH_FUNCTION_AMOUNT, KTop_FREQUENCY);
        cmsService.insertToSketch(cleanList, sketch);
        cmsService.countMinSketch(cleanList, sketch);
        List<Map.Entry<String, Integer>> parsedKTop = sketch.getkTop()
                .parseMapKTopToList(sketch.getkTop());
        Map<String, Long> words_Occurrence = SORT_SERVICE.sortWords(cleanList);
        FileWriter fileWriter = pathOutputFile();
        if ((fileWriter != null)) {
            fileWriter.writeToFile(parsedKTop, words_Occurrence);
        }
        PRINT_SERVICE.printOutputTable(parsedKTop, words_Occurrence);
    }

    private FileWriter pathOutputFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDo you want to write data to the file?");
        System.out.println("Press 1, YES.");
        System.out.println("Press 2, NO.");
        while (true) {
            System.out.print("Enter number here: ");
            try {
                int i = scanner.nextInt();
                if (i == 1) {
                    System.out.println("\nYou can enter an absolute path for the output "
                            + "file (external directory), such as 'D://output.txt'.");
                    System.out.println("Or just enter a name, a new file will be saved in the root directory."
                            + " Then refresh a directory accordingly.");
                    System.out.print("Please enter path here:");
                    scanner = new Scanner(System.in);
                    return new FileWriter(scanner.nextLine());
                } else if (i == 2){
                    return null;
                } else {
                    System.out.println("You didn't choose any variant! Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter string path!");
                scanner = new Scanner(System.in);
            }
        }
    }
}
