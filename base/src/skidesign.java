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
O(2^n) doesn't finish for n=50

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
                origHills = new int[n];
                for (int i = 0; i < n; i++) {
                    origHills[i] = Integer.valueOf(f.readLine());
                }
                Arrays.sort(origHills);

                out.println(minCost(Arrays.copyOf(origHills,n)));
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
    assume hills is sorted and of length n, divide by zero error?
    */
    static int minCost(int[] hills) {
        while (rangeLeft(hills) > 0) {
            if (numHeads(hills) < numTails(hills) || rangeLeft(hills) == 1) {
                incrementHead(hills, Math.min(headSpace(hills), rangeLeft(hills)));
            } else if (numHeads(hills) == numTails(hills)) {
                int split = rangeLeft(hills) / 2;
                incrementHead(hills, Math.min(split,headSpace(hills)));
                decrementTail(hills, Math.min(split, tailSpace(hills)));
            } else {
                decrementTail(hills, Math.min(tailSpace(hills), rangeLeft(hills)));
            }
        }
        return evalCost(hills, origHills);
    }

    private static int numTails(int[] hills) {
        int retVal = 1;
        for (int i = n-2; i >= 0; i--) {
            if (hills[i] == hills[n-1]) {
                retVal++;
            } else {
                break;
            }
        }
        return retVal;
    }

    private static int numHeads(int[] hills) {
        int retVal = 1;
        for (int i = 1; i < n; i++) {
            if (hills[i] == hills[0]) retVal++;
            else {
                break;
            }
        }
        return retVal;
    }

    private static void decrementTail(int[] hills, int amt) {
        int end = numTails(hills);
        for (int i = 0; i < end; i++) {
            hills[n-i-1] -= amt;
        }
    }

    private static void incrementHead(int[] hills, int amt) {
        int end = numHeads(hills);
        for (int i = 0; i < end; i++) {
            hills[i] += amt;
        }
    }

    private static int headSpace(int[] hills) {
        return hills[numHeads(hills)]-hills[0];
    }

    private static int tailSpace(int[] hills) {
        int retVal = hills[n-1] - hills[n-numTails(hills)-1];
        return retVal;
    }

    private static int rangeLeft(int[] hills) {
        return hills[n-1]-hills[0]-17;
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

    /* Assume hills is sorted except for first element and length n*/
    static void cleanUpBottom(int[] hills) {
        for (int i = 1; i < n && hills[i] < hills[0]; i++) {
            if (i == n-1 || hills[i+1] != hills[i]) {
                int swapHold = hills[i];
                hills[i] = hills[0];
                hills[0] = swapHold;
                break;
            }
        }
    }

    /* Assume hills is sorted except for last element and length n*/
    static void cleanUpTop(int[] hills) {
        for (int i = n-2; i >= 0 && hills[i] > hills[n-1]; i--) {
            if (i == 0 || hills[i-1] != hills[i]) {
                int swapHold = hills[i];
                hills[i] = hills[n-1];
                hills[n-1] = swapHold;
                break;
            }
        }
    }
}
