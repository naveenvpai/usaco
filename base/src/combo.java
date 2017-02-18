/*
ID: pai.nav1
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.*;

/*
Parse.
Find the number of combinations that are within two turns of at least one of two combinations
for each dial on a three dial circular lock, with N digits.

Contextualize.
If |value1-value2| is <= 2 then its within two
Could however be |50-1| and considered valid
Make sure to delete overlaps (use a SortedSet?)
N in [1,100] so int safe

Solution ideas
Generate the valid combos for each (represented as Strings), put them all in a SortedSet, print the count
Brute force using permutations code: n^3

Errors.
Brute force doesn't meet time constraint: permutation generation is the problem

*/

public class combo {

    static int n;

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("combo.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

                n = Integer.valueOf(f.readLine());
                String allNums = "";
                for (int i = 1; i <= n; i++) {
                    allNums = allNums+""+String.valueOf(i);
                }

                String johnCombo = f.readLine().replaceAll(" ","");
                String masterCombo = f.readLine().replaceAll(" ", "");

                String[] allCombos = permutations(allNums, 3);
                int validCount = 0;
                for (String combo : allCombos) {
                    if (withinTwo(combo, johnCombo, masterCombo)) validCount++;
                }

                out.println(validCount);
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

    static String[] permutations(String original, int size) {
        String[] retVal = new String[(int)Math.pow(original.length(), size)];
        for (int c = 0; c < size; c++) {
            int numChildPerms = (int)Math.pow(original.length(), size-c-1);
            for (int i = 0; i < retVal.length; i++) {
                int currIndex = (i/numChildPerms)%original.length();
                retVal[i] = ((c > 0 ) ? retVal[i] : "")+original.charAt(currIndex);
            }
        }
        return retVal;
    }

    static boolean withinTwo(String thisCombo, String johnCombo, String masterCombo) {
        return withinTwo(thisCombo, johnCombo) || withinTwo(thisCombo, masterCombo);
    }

    static boolean withinTwo(String a, String b) {
        for (int c = 0; c < a.length(); c++) {
            if (!withinTwo(intValue(a,c),intValue(b,c))) return false;
        }
        return true;
    }

    static boolean withinTwo(int a, int b) {
        int diff = Math.abs(a-b);
        return diff <= 2 || diff >= n-2;
    }

    static int intValue(String str, int index) {
        return Integer.valueOf(str.substring(index,index+1));
    }

}
