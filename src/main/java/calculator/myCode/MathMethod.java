package calculator.myCode;

import java.util.ArrayList;

public class MathMethod {

    public boolean operators(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    public void logicMethod(ArrayList<Integer> x, char sym) {
        int a = x.remove(x.size() - 1);
        int b = x.remove(x.size() - 1);
        switch (sym) {
            case '+' -> x.add(b + a);
            case '-' -> x.add(b - a);
            case '*' -> x.add(b * a);
            case '/' -> x.add(b / a);
        }
    }

    public int inputString(String s) {
        int result = 0;


        ArrayList<Character> characterArrayList = new ArrayList<>();
        ArrayList<Integer> digitsArrayList = new ArrayList<>();


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;

            while (operators(c)) {
                if (c == '*') {

                }
            }
        }
        return result;
    }

}
