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
                for (int i = 0; i < triangle.length; i++) {
                    String[] inputs = f.readLine().split(" ");
                    int[] row = new int[inputs.length];
                    for (int j = 0; j < row.length; j++) {
                        row[j] = Integer.valueOf(inputs[j]);
                    }
                    triangle[i] = row;
                }

                int maxSum = 0;
                Point[] sums = new Point[]{new Point(triangle[0][0],0)};
                for (int row = 1; row < triangle.length; row++) {
                    Point[] nextSums = new Point[sums.length*2];
                    for (int i = 0; i < nextSums.length; i++) {
                        Point parent = sums[i/2];
                        int myIndex = parent.y + i%2;
                        nextSums[i] = new Point(parent.x+triangle[row][myIndex],myIndex);
                        if (row == triangle.length-1) maxSum = Math.max(nextSums[i].x, maxSum);
                    }
                    sums = nextSums;
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

    static ArrayList<int[]> permutations(int numIntsToUse, int size) {
        ArrayList<int[]> retVal = new ArrayList<>();
        int numPerms = (int)Math.pow(numIntsToUse, size);
        for (int i = 0 ; i < numPerms; i++) {
            retVal.add(new int[size]);
        }
        for (int c = 0; c < size; c++) {
            int numChildPerms = (int)Math.pow(numIntsToUse, size-c-1);
            for (int i = 0; i < numPerms; i++) {
                int currIndex = (i/numChildPerms)%numIntsToUse;
                retVal.get(i)[c] = currIndex;
            }
        }
        return retVal;
    }
}
