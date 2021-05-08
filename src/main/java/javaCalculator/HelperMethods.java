package javaCalculator;

import java.util.LinkedList;
import java.util.Scanner;

public class HelperMethods {

    public boolean mathOperations(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    /**
     * Старая запись:
     * switch (symbol) {
     * case '+':
     * number.add(num2 + num1);
     * break;
     */
    public void mathActions(LinkedList<Integer> number, char symbol) {
        int num1 = number.removeLast();
        int num2 = number.removeLast();
        switch (symbol) {

            // case label_1, label_2, ..., label_n -> expression;|throw-statement;|block
            /* JAVA 12 (2019) new syntax */
            case '+' -> number.add(num2 + num1);
            case '-' -> number.add(num2 - num1);
            case '*' -> number.add(num2 * num1);
            case '/' -> number.add(num2 / num1);
        }
    }

    public void initial() {

        Algorithm algorithm = new Algorithm();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input Anything: ");
        String s = scan.nextLine();

        System.out.println(algorithm.makerCalc(s));
    }
}
