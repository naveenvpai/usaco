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

Repeatedly add to the head (all lowest elements) and subtract from the tail (all highest elements) in some optimal combination.
can't guarantee to work for every list!

Medianize the list: adjust it so that each element is within optimal range from the median
doesn't work because median might not give lowest cost for small lists

Idealize the list: find the center of the interval with width 17 that excludes the least number of elements
and adjust the excluded elements to be within the range (doesn't work bc distance from range counts as well as number changed)

Find best cost: try every such center above finding the resulting cost at each

Errors.
O(2^n) doesn't finish for n=50 (maybe worse)

*/

public class skidesign {

    static int n;
    static int idealRange = 17;
    static int[] hills;

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("skidesign.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

                n = Integer.valueOf(f.readLine());
                hills = new int[n];
                for (int i = 0; i < n; i++) {
                    hills[i] = Integer.valueOf(f.readLine());
                }
                Arrays.sort(hills);

                out.println(findBestCost(hills));
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

    static int findBestCost(int[] hills) {
        int bestCost = Integer.MAX_VALUE;
        for (int i = hills[0]; i < hills[n-1]; i++) {
            int currCost = 0;
            for (int j = 0; j < n; j++) {
                int diff = 0;
                if (hills[j] < i) {
                    diff = i-hills[j];
                }
                else if (hills[j] > i+idealRange) {
                    diff = hills[j]-(i+idealRange);
                }
                currCost += diff*diff;
            }
            bestCost = Math.min(currCost, bestCost);
        }
        return bestCost;
    }

}
