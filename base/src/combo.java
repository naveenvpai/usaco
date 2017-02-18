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
Brute force doesn't meet time constraint: permutation generation is the problem.
Represented combo as string, even though dial values may be more than one digit: changed to int array
*/

public class combo {
    static int n;
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("combo.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

                n = Integer.valueOf(f.readLine());
                int[] johnCombo = comboFrom(f.readLine());
                int[] masterCombo = comboFrom(f.readLine());

                ArrayList<String> johnSimilars = getPassingCombos(johnCombo);
                ArrayList<String> masterSimilars = getPassingCombos(masterCombo);

                TreeSet<String> validCombos = new TreeSet<>();
                for (String sim : johnSimilars) {
                    validCombos.add(sim);
                }
                for (String sim : masterSimilars) {
                    validCombos.add(sim);
                }

                out.println(validCombos.size());
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

    static int[] comboFrom(String spaceSep) {
        String[] vals = spaceSep.split(" ");
        int[] retVal = new int[vals.length];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = Integer.valueOf(vals[i]);
        }
        return retVal;
    }

    static ArrayList<String> getPassingCombos(int[] original) {
        String[] validDiffs = permutations("012",3);
        String[] validSigns = permutations("-+", 3);
        ArrayList<String> retVal = new ArrayList<>();
        for (String diff : validDiffs) {
            for (String sign : validSigns) {
                retVal.add(getCombo(original, diff, sign));
            }
        }
        return retVal;
    }

    static String getCombo(int[] original, String diff, String sign) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < original.length; i++) {
            int signMod = sign.charAt(i) == '+' ? 1 : -1;
            int currVal = (original[i]+intValue(diff,i)*signMod);
            currVal = (currVal+n)%n;
            sb.append(currVal);
        }
        return sb.toString();
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

    static int intValue(String str, int index) {
        return Integer.valueOf(str.substring(index, index+1));
    }
}
