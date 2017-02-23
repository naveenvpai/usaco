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
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("ariprog.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
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
}
