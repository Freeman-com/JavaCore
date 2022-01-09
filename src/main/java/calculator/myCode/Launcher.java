package calculator.myCode;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input Anything: ");
        String s = scan.nextLine();

        MathMethod mathMethod = new MathMethod();

//        System.out.println(mathMethod.mathMeth(s));

    }


}
