/*
ID: pai.nav1
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.*;

/*
Parse.
Find the minimum board length required to cover C stalls arranged according to specified indices in a group of S stalls, using a maximum of M boards.

Contextualize.
All fields integer safe.
Do not assume indices are given in ascending order!

Solution ideas.
Use the greedy approach specified in the reading: keep removing boards

Errors.
Assumed ordered indices.
Subtracted the gaps from S while really needed to be subtracted from the max-min range.
Next time, use method stubs to organize thinking. 
*/

public class barn1 {
    static int[] getSortedCows(int c, BufferedReader f) throws IOException {
        int[] retVal = new int[c];        
        for (int i = 0; i < c; i++) {
          retVal[i] = Integer.valueOf(f.readLine());
        }
        Arrays.sort(retVal);
        return retVal;
    }

    static int[] getSortedDiffs(int c, int[] cows) {
       int[] retVal = new int[c-1];
       for (int i = 1; i < c; i++) {
          retVal[i-1] = Math.abs(cows[i]-cows[i-1]-1);
       }
       Arrays.sort(retVal);
       return retVal;
    }

    static int getTotal(int m, int c, int[] diffs) {
      int retVal = c;
      for (int i = 0; i < diffs.length-(m-1); i++) {
         retVal += diffs[i];
      }
      return retVal;
    }
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("barn1.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
                String[] firstLine = f.readLine().split(" ");
                int m = Integer.valueOf(firstLine[0]);
                int s = Integer.valueOf(firstLine[1]);
                int c = Integer.valueOf(firstLine[2]);
                int[] cows = getSortedCows(c,f);
                int[] diffs = getSortedDiffs(c,cows);
                out.println(getTotal(m,c,diffs));
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
}
