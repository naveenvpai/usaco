/*
ID: pai.nav1
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.*;

/*
Parse.

Contextualize.

Solution ideas.

Errors.

*/

public class milk3 {

    static int A, B, C;
    static int[] capacities;
    static HashSet<String> visited;

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("milk3.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
                
                String[] inputs = f.readLine().split(" ");
                A = Integer.valueOf(inputs[0]);
                B = Integer.valueOf(inputs[1]);
                C = Integer.valueOf(inputs[2]);
                capacities = new int[]{A,B,C};

                TreeSet<Integer> result = new TreeSet<>();
                visited = new HashSet<>();
                ArrayList<int[]> currRound = new ArrayList<>();

                int[] initial = new int[]{0,0,C};
                result.add(C);
                currRound.add(initial);
                visited.add(stringify(initial));
                
                while(!currRound.isEmpty()) {
                    currRound = getNextRound(currRound);
                    for (int[] three : currRound) {
                        if (three[0] == 0) result.add(three[2]);
                        visited.add(stringify(three));
                    }
                }

                printResult(result,out);
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

    static String stringify(int[] three) {
        return three[0]+" "+three[1]+" "+three[2];
    }

    static ArrayList<int[]> getNextRound(ArrayList<int[]> currRound) {
        ArrayList<int[]> retVal = new ArrayList<>();
        for (int[] three : currRound) {
            retVal.addAll(getNextSubRound(three));
        }
        return retVal;
    }

    static ArrayList<int[]> getNextSubRound(int[] three) {
        ArrayList<int[]> retVal = new ArrayList<>();
        int[][] pours = new int[][]{
            pour(three,0,1,2),
            pour(three,0,2,1),
            pour (three,1,0,2),
            pour(three,1,2,0),
            pour(three,2,0,1),
            pour(three,2,1,0)
        };
        for (int[] newThree : pours) {
            if (newThree != null) retVal.add(newThree);
        }
        return retVal;
    }

    static int[] pour(int[] three, int startIndex, int endIndex, int noChangeIndex) {
        int[] retVal = Arrays.copyOf(three, 3);
        int amt = Math.min(three[startIndex], capacities[endIndex]-three[endIndex]);
        retVal[startIndex] -= amt;
        retVal[endIndex] += amt;
        return visited.contains(stringify(retVal)) ? null : retVal;
    }

    static void printResult(TreeSet<Integer> tree, PrintWriter out) throws IOException {
        Iterator treeIt = tree.iterator();
        while(treeIt.hasNext()) {
            out.print(treeIt.next());
            if (treeIt.hasNext()) out.print(" ");
        }
    }

}
