/*
ID: pai.nav1
LANG: JAVA
TASK: palsquare
*/

import java.awt.*;
import java.io.*;
import java.util.*;

/*

Parse.
Given a base b in [2,20],
Print all base b integers in [1,300] whose values squared are palindromes as well as there squared value (also in base b).

Contextualize.
Have to express ints in base b using A, B, etc to represent digits 10, 11, etc.

Solution ideas.
Go through all base 10 integers in [1,300] and find their squared value expressed in base b.
Print those that are palindromes.

*/

public class palsquare {

    private static HashMap<Integer, Character> digitMap;
    static {
        digitMap  = new HashMap<>();
        digitMap.put(0, '0');
        digitMap.put(1, '1');
        digitMap.put(2, '2');
        digitMap.put(3, '3');
        digitMap.put(4, '4');
        digitMap.put(5, '5');
        digitMap.put(6, '6');
        digitMap.put(7, '7');
        digitMap.put(8, '8');
        digitMap.put(9, '9');
        digitMap.put(10, 'A');
        digitMap.put(11, 'B');
        digitMap.put(12, 'C');
        digitMap.put(13, 'D');
        digitMap.put(14, 'E');
        digitMap.put(15, 'F');
        digitMap.put(16, 'G');
        digitMap.put(17, 'H');
        digitMap.put(18, 'I');
        digitMap.put(19, 'J');
    }

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("palsquare.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
                int myBase = Integer.valueOf(f.readLine());
                for (int i = 1; i <= 300; i++) {
                    String square = representBaseB(i*i, myBase);
                    if (isPalindrome(square)) {
                        out.println(representBaseB(i, myBase) + " " + square);
                    }
                }
                out.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean isPalindrome(String number) {
        for (int i = 0; i < number.length()/2; i++) {
            if (number.charAt(i) != number.charAt(number.length()-i-1)) return false;
        }
        return true;
    }

    static String representBaseB(int num, int base) {
        int amountLeft = num;
        char[] retVal = null;
        boolean hasInitRetval = false;
        int length = 0;
        while (amountLeft != 0) {
            int place = highestPower(amountLeft, base);
            if (!hasInitRetval) {
                length = place+1;
                retVal = new char[length];
                for (int i = 0; i < length; i++) {
                    retVal[i] = digitMap.get(0);
                }
                hasInitRetval = true;
            }
            int raised = (int)Math.pow((double)base, (double)place);
            int value = amountLeft/raised;
            retVal[length-place-1] = digitMap.get(value);
            amountLeft -= value*raised;
        }
        return new String(retVal);
    }

    private static int highestPower(int number, int base) {
        double exact = Math.log((double)number)/Math.log((double)base);
        return (int)exact;
    }
}
