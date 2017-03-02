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
    static int[][] triangle;
    static HashMap<Point, Integer> memoSum;
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("numtri.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

                triangle = new int[Integer.valueOf(f.readLine())][];
                memoSum = new HashMap<>();
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
                out.println(maxSum(0,0,triangle[0][0]));
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
    static int maxSum(int row, int col, int prevSum) {
        if (row == triangle.length-1) return prevSum;
        Integer lookup = memoSum.get(new Point(row,col));
        if (lookup != null) return lookup + prevSum;
        int sumBelow = Math.max(maxSum(row+1,col,triangle[row+1][col]), maxSum(row+1, col+1,triangle[row+1][col+1]));
        memoSum.put(new Point(row,col), sumBelow);
        return prevSum+sumBelow;
    }
}

