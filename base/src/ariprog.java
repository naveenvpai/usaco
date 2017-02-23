/*
ID: pai.nav1
LANG: JAVA
TASK: ariprog
*/


import java.io.*;
import java.util.*;

/*
Parse.

Contextualize.

Solution ideas.

Errors.

*/

public class ariprog {

    static int n;
    static int m;
    static TreeSet<Integer> bisquaresTree;
    static HashSet<Integer> bisquaresHash;

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("ariprog.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

                n = Integer.valueOf(f.readLine());
                m = Integer.valueOf(f.readLine());

                initBisquares();

                ArrayList<Integer> bisquares = new ArrayList<>(bisquaresTree);
                TreeMap<Integer, ArrayList<Integer>> bToA = new TreeMap<>();

                for (int i = 0; i < bisquares.size()-(n-1); i++) {
                    int a = bisquares.get(i);
                    for (int j = i+1; j < bisquares.size() && bisquares.get(j)-a <= 2*m*m/(n-1); j++) {
                        int b = bisquares.get(j) - a;
                        int k;
                        for (k = 1; k < n; k++) {
                            if (!bisquaresHash.contains(a + b * k)) break;
                        }
                        if (k == n) {
                           if (bToA.containsKey(b)) {
                               bToA.get(b).add(a);
                           }
                           else {
                               ArrayList<Integer> begin = new ArrayList<>();
                               begin.add(a);
                               bToA.put(b,begin);
                           }
                        }
                    }
                }
                if (bToA.isEmpty()) {
                    out.println("NONE");
                }
                else {
                    for (int b : bToA.navigableKeySet()) {
                        ArrayList<Integer> myAs = bToA.get(b);
                        for (int a : myAs) {
                            out.println(a+" "+b);
                        }
                    }

                }
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

    static void initBisquares() {
        bisquaresTree = new TreeSet<>();
        bisquaresHash = new HashSet<>();
        for (int i = 0; i <= m; i++) {
            for (int j = i; j <=m; j++) {
                int bsq = i * i + j * j;
                bisquaresTree.add(bsq);
                bisquaresHash.add(bsq);
            }
        }
    }
}
