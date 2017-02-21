/*
ID: pai.nav1
LANG: JAVA
TASK: skidesign
*/

import java.io.*;
import java.util.*;

/*
Parse.
Find the minimum cost of reducing the range of a list to 17 where the cost is the sum (finalVal-initialVal)^2 for each element

Contextualize.
[1,1000] elements each in [0,100]

Solution ideas.

Reduce the head and tail of the list repeatedly until desired range achieved.
tricky because you want to spread out the reductions as much as possible x^2+y^2=(x+y)^2-2xy
at the same time, you the tail or head may be favorable depending on how close the min/max and second smallest/largest elements are
(eg. 3 3 3 5 6 7 20)

Recursively find the cost for each possible head/tail reduction along the way (less thinking, less efficiency) 

Errors.

*/

public class skidesign {
    static int n;
    static int[] origHills;
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("skidesign.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
                
                n = Integer.valueOf(f.readLine());
                for (int i = 0; i < n; i++) {
                    origHills[i] = Integer.valueOf(f.readLine());
                }
                Arrays.sort(origHills);

                out.println(minCost(origHills));
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

    /*
    assume hills is of length n
    */
    static int minCost(int[] hills) {
        int[] safeHills1 = Arrays.copyOf(hills, n);
        Arrays.sort(safeHills1);
        int thisRange = safeHills1[n]-safeHills1[0];
        if (thisRange == 17) {
            return evalCost(origHills, safeHills1);
        }
        int[] safeHills2 = Arrays.copyOf(hills, n);
        Arrays.sort(safeHills2);
        safeHills1[0] += 1;
        safeHills2[n-1] -= 1;
        return Math.min(minCost(safeHills1), minCost(safeHills2));
    }
    
    /*
    assume both are sorted and of length n
    */
    static int evalCost(int[] hills1, int[] hills2) {
        int retVal = 0;
        for (int i = 0; i < n; i++) {
            int diff = hills1[i] - hills2[i];
            retVal += diff*diff;
        }
        return retVal;
    }
}
