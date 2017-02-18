/*
ID: pai.nav1
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.text.Collator;
import java.util.*;

/*
Parse.
Given a set of n digits, find the number of solutions to a cryptarithm (filling in the digits to multiplication by partial products)

Contextualize.
Non-zero digits are given, therefore n ≤ 9 (scalability not an issue)

Solution ideas.
Brute force: generate and check
- seems better because polynomial time possible

Check as you go
Greediness?


 */

public class crypt1 {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

                String digits = getDigits(f);
                String[] topPerms = permutations(digits, 3);
                String[] bottomPerms = permutations(digits, 2);
                int validCombos = numSolved(topPerms, bottomPerms, digits);

                out.println(validCombos);
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

    static String getDigits(BufferedReader f) throws IOException {
        return f.readLine().replace(" ", "");
    }

    static String[] permutations(String original, int size) {
        String[] retVal = new String[(int)Math.pow(original.length(), size)];

        return retVal;
    }

    static int numSolved(String[] tops, String[] bottoms, String digits) {
        int retVal = 0;
        for (String top : tops) {
            for (String bottom : bottoms) {
                if (cryptSolved(top,bottom,digits)) retVal++;
            }
        }
        return retVal;
    }

    static boolean cryptSolved(String threeDig, String twoDig, String validDigits) {
        String topPartial = String.valueOf(Integer.valueOf(threeDig)*Integer.valueOf(String.valueOf(twoDig.charAt(1))));
        String bottomPartial = String.valueOf(Integer.valueOf(threeDig)*Integer.valueOf(String.valueOf(twoDig.charAt(0))));
        String total = String.valueOf(Integer.valueOf(threeDig)*Integer.valueOf(twoDig));
        return allValid(validDigits, topPartial, bottomPartial, total);
    }

    static boolean allValid(String validDigits, String... testStrings) {
        for (String str : testStrings) {
            for (int i = 0; i < str.length(); i++) {
                if (!validDigits.contains(String.valueOf(str.charAt(i)))) return false;
            }
        }
        return true;
    }
}
