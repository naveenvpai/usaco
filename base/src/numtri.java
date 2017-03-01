/*
ID: pai.nav1
LANG: JAVA
TASK: numtri
 */

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/*
   Parse.

   Contextualize.

   Solution ideas.

   Errors.

 */

public class numtri {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("numtri.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

                int[][] triangle = new int[Integer.valueOf(f.readLine())][];
                int max = 0;
                for (int i = 0; i < triangle.length; i++) {
                    String[] inputs = f.readLine().split(" ");
                    int[] row = new int[inputs.length];
                    for (int j = 0; j < row.length; j++) {
                        row[j] = Integer.valueOf(inputs[j]);
                        max = Math.max(row[j], max);
                    }
                    triangle[i] = row;
                }

                int maxSum = 0;
                long idealSum = ((long)max)*((long)triangle.length);
                long numPerms = (long)Math.pow(2,triangle.length-1);
                for (long perm = numPerms-1; perm >= 0; perm--) {
                    String thisSeq = fillZeros(Long.toBinaryString(perm),triangle.length-1);
                    int index = 0;
                    int currSum = triangle[0][0];                                 
                    for (int i = 0; i < thisSeq.length() && i < triangle.length-1; i++) {
                        index += thisSeq.charAt(i) == '0' ? 0 : 1;
                        currSum += triangle[i+1][index];
                    }
                    maxSum = Math.max(currSum, maxSum);
                    if (maxSum == idealSum) break;
                }

                out.println(maxSum);
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

    static String fillZeros(String head, int totalLength) {
        int remainingLength = totalLength - head.length();
        StringBuilder zeros = new StringBuilder();
        for (int i = 0; i < remainingLength; i++) {
            zeros.append("0");
        }
        return zeros.toString() + head;
    }
}
