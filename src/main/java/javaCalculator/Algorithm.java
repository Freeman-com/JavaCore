package javaCalculator;

import java.util.LinkedList;

public class Algorithm {

    HelperMethods helperMethods = new HelperMethods();

    public int makerCalc(String s) {

        LinkedList<Integer> numbersLinkedList = new LinkedList<>();
        LinkedList<Character> characterLinkedList = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;

            if (helperMethods.mathOperations(c)) {
                while (!characterLinkedList.isEmpty())

                    helperMethods.mathActions(numbersLinkedList, characterLinkedList.removeLast());
                characterLinkedList.add(c);
            } else {
                StringBuilder operand = new StringBuilder();
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    operand.append(s.charAt(i++));
                }
                --i;
                numbersLinkedList.add(Integer.parseInt(operand.toString()));
            }
        }

        while (!characterLinkedList.isEmpty())
            helperMethods.mathActions(numbersLinkedList, characterLinkedList.removeLast());
        return numbersLinkedList.get(0);
    }
}
