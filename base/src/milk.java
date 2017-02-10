/*
ID: pai.nav1
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.*;

public class milk {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("milk.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

                out.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        SortedMap<Integer, Integer> test = new TreeMap<>();
        test.put(4,6);
        test.put(2,7);
        test.put(3,5);
        test.put(1,8);
        for (int x : test.keySet()) {
          System.out.println(x);
        }
    }
}
