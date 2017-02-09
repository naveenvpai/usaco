/*
ID: pai.nav1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;
/*

Parse.
Find valid names using possible letter representations of a 1-12 digit sequence on a TouchTone sequence.

Contextualize.
There are 3^n possible letter sequences resulting from a serial number with n in [1,12].
There are 5,000 names to search.

Solution ideas.
brute force: find all possible sequences and validate each.
alphabetical: check if there are valid names starting with the substring you generate as you creat each sequence.
data dump: find the serial numbers corresponding to each valid name and find all matching the given serial number.

Check.
remembered to print corresponding valid name instead of just the serial.

Errors.
incorrect filename for dict.txt.
forgot to print NONE if necessary.
 */
public class  namenum {

    private static HashMap<String, String> letterToKey;
    static {
        letterToKey = new HashMap<>();
        letterToKey.put("A", "2");
        letterToKey.put("B", "2");
        letterToKey.put("C", "2");
        letterToKey.put("D", "3");
        letterToKey.put("E", "3");
        letterToKey.put("F", "3");
        letterToKey.put("G", "4");
        letterToKey.put("H", "4");
        letterToKey.put("I", "4");
        letterToKey.put("J", "5");
        letterToKey.put("K", "5");
        letterToKey.put("L", "5");
        letterToKey.put("M", "6");
        letterToKey.put("N", "6");
        letterToKey.put("O", "6");
        letterToKey.put("P", "7");
        letterToKey.put("R", "7");
        letterToKey.put("S", "7");
        letterToKey.put("T", "8");
        letterToKey.put("U", "8");
        letterToKey.put("V", "8");
        letterToKey.put("W", "9");
        letterToKey.put("X", "9");
        letterToKey.put("Y", "9");
    }

    public static void main(String[] args) {
        try {
            try {
                BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
                String mySerial = f.readLine();
                ArrayList<String> validNames = getValidNames("dict.txt");
                ArrayList<String> validSerials = getValidSerials(validNames);
                boolean foundMatch = false;
                for (int i = 0; i < validSerials.size(); i++) {
                    if (validSerials.get(i).equals(mySerial)) {
                        out.println(validNames.get(i));
                        foundMatch = true;
                    }
                }
                if (!foundMatch) out.println("NONE");
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

    public static String getSerialNumber(String name) {
        String retVal = "";
        for (int i = 0; i < name.length(); i++) {
            retVal += letterToKey.get(name.substring(i,i+1));
        }
        return retVal;
    }

    public static ArrayList<String> getValidNames(String fileName) {
        ArrayList<String> retVal = new ArrayList<>();
        try {
            BufferedReader f = new BufferedReader(new FileReader(fileName));
            while (f.ready()) {
                retVal.add(f.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public static ArrayList<String> getValidSerials(ArrayList<String> validNames) {
        ArrayList<String> retVal = new ArrayList<>();
        for (String valid : validNames) {
            retVal.add(getSerialNumber(valid));
        }
        return retVal;
    }
}
