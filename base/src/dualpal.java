/*
ID: pai.nav1
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.*;

/*
Parse.
Print the first N numbers greater than S that are a palindrome in two or more bases

Contextualize.
N in [1,15], so not that many repetitions
Check bases [2,10]
S in (0,10000), so could be fairly high

Solution ideas.
Starting at S+1 find all base [2,10] representations and count the number of palindromes.

Check.
fairly confident in helper methods (tested all except highestPower, which isPalindrome relies on_
output format.
*/

public class dualpal {

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
                BufferedReader f    = new BufferedReader(new FileReader("dualpal.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
                String[] input = f.readLine().split(" ");
                int n = Integer.valueOf(input[0]);
                int s = Integer.valueOf(input[1]);
                int numFound = 0;
                int x = s+1;
                while (numFound < n) {
                  if (numPalindromeRepresentations(x) >= 2) {
                    out.println(x);
                    numFound++;
                  }
                  x++;
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
    
    static int numPalindromeRepresentations(int x) {
      int retVal = 0;
      for (int base = 2; base <= 10; base++) {
        if (isPalindrome(representBaseB(x, base))) retVal++;
      }
      return retVal;
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
