/*
ID: pai.nav1
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.*;

public class friday {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f = new BufferedReader(new FileReader("friday.in"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
