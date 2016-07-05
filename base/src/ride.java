/*
ID: pai.nav1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.HashMap;

class ride {
    static char[] ALPHABET = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    static HashMap<Character, Integer> LETTER_MAP;
    public static void main(String[] args) {
        try {
            initializeCharMap();
            BufferedReader f = new BufferedReader(new FileReader("ride.in"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
            String comet = f.readLine();
            String group = f.readLine();
            if (decode(comet) == decode(group)) {
                out.println("GO");
            } else {
                out.println("STAY");
            }
            out.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static int decode(String text) {
        int prod = 1;
        for (int i = 0; i < text.length(); i++) {
            Integer val = (Integer)LETTER_MAP.get(new Character(text.charAt(i)));
            prod *= val.intValue();
        }
        return prod%47;
    }
    private static void initializeCharMap() {
        LETTER_MAP = new HashMap<Character, Integer>();
        for (int i = 0; i < ALPHABET.length; i++) {
            LETTER_MAP.put(new Character(ALPHABET[i]), new Integer(i+1));
        }
    }
}
