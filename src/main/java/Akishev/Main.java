package Akishev;

import Akishev.changeover.Auto;
import Akishev.changeover.Manual;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nThis is an application, which will find a top k (most frequent) element(s) \nusing a "
                + "probabilistic data structure which is called Count Min Sketch.");
        System.out.println("\nPlease choose, how do you want to use this application:");
        System.out.println(" - Press 1, if you want to start application in "
                + "the auto mode (with default parameters).");
        System.out.println(" - Press 2, if you want to start application in "
                + "the manual mode (you are able to input your parameters).");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter number here: ");
            try {
                int i = scanner.nextInt();
                if (i == 1) {
                    Auto.startAutoMode().startMode();
                    break;
                } else if (i == 2) {
                    Manual.startManualMode().startMode();
                    break;
                } else {
                    System.out.println("You didn't choose correct variant! Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter number!");
                scanner = new Scanner(System.in);
            }
        }
    }
}
