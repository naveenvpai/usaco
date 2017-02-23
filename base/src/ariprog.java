/*
ID: pai.nav1
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/*
Parse.

Contextualize.

Solution ideas.

Errors.

*/

public class ariprog {

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("ariprog.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

                int n = Integer.valueOf(f.readLine());
                int m = Integer.valueOf(f.readLine());
                HashSet<Integer> bisquares = bisquares(m);

                //trim endponts
                int numPrinted = 0;
                int lastBisquare = 2*m*m;
                for (int b = 1; b <= lastBisquare/(n-1); b++) {
                    Iterator aIter = bisquares.iterator();
                    while (aIter.hasNext()) {
                        int a = (int)aIter.next();
                        if (a > lastBisquare-b*(n-1)) continue;
                        int k;
                        for (k = 0; k < n; k++) {
                            int ak = a + b * k;
                            if (!bisquares.contains(ak)) break;
                        }
                        if (k == n) {
                            out.println(a+" "+b);
                            numPrinted++;
                        }
                    }
                }

                if (numPrinted==0) out.println("NONE");
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

    static HashSet<Integer> bisquares(int m) {
        HashSet<Integer> retVal = new HashSet<>();
        for (int i = 0; i <= m; i++) {
            for (int j = i; j <=m; j++) {
                retVal.add(i*i+j*j);
            }
        }
        return retVal;
    }
}
