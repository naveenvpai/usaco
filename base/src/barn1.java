/*
ID: pai.nav1
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.*;

/*
Parse.
Find the minimum total board length needed to cover C stalls using a maximum of M boards.The cows are arranged
in the S stalls according to given indices.

Contextualize.
M in [1,50] so at least 1 board can be used
S in [1, 200] so array and integer safe
C in [1, S]
stall numbers start at 1

Solution ideas.

*/

public class barn1 {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
                String[] firstLineInput = f.readLine().split(" ");
                int m = Integer.valueOf(firstLineInput[0]);
                int s = Integer.valueOf(firstLineInput[1]);
                int c = Integer.valueOf(firstLineInput[2]);
                int[] cows = new int[c];
                TreeSet<Integer> diffs = new TreeSet<>();
                for (int i = 0; i < c; i++) {
                    cows[i] = Integer.valueOf(f.readLine());
                }
                Arrays.sort(cows);
                for (int i = 1; i < c; i++) {
                    diffs.add(cows[i]-cows[i-1]);
                }
                int covered = c;
                for (int i = 0; i < m; i++) {
                    covered += diffs.pollFirst();
                }
                out.println(covered);
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
