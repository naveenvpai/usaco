/*
ID: pai.nav1
LANG: JAVA
TASK: skidesign
*/

import java.io.*;
import java.util.*;

/*
Parse.

Contextualize.

Solution ideas.

Errors.

*/

public class skidesign {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("skidesign.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
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
