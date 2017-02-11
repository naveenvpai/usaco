/*
ID: pai.nav1
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.*;

/*
Parse.
Given a list of available prices and quantities find the lowest cost required to satisfy a certain quantity.

Context.
Quanity Q in [0, 2 million] presents integer complications (could be 16 bit). use long?
N in [0,5000] prices to process
Need to handle 0 for both!

Solution ideas.
Order prices, start at lowest keep going until quanitity is fulfilled.

Errors.
Thought input was quanity-price but it was the other way around.
Didn't account for HashMap replacing repeats: instead check if the existing price already exists then add to the current quantity.
*/

public class milk {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("milk.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
                String[] firstLineInput = f.readLine().split(" ");
                long desiredQuantity = Long.valueOf(firstLineInput[0]);
                int numPrices = Integer.valueOf(firstLineInput[1]);
                SortedMap<Integer, Long> priceToQuantity = new TreeMap<>();
                for (int i = 0; i < numPrices; i++) {
                    String[] thisLineInput = f.readLine().split(" ");
                    int price = Integer.valueOf(thisLineInput[0]);
                    long value = Long.valueOf(thisLineInput[1]);
                    if(!priceToQuantity.containsKey(price)) {
                        priceToQuantity.put(price, value);
                    }
                    else {
                        priceToQuantity.replace(price, priceToQuantity.get(price)+value);
                    }
                }
                out.println(findLowestPrice(priceToQuantity,desiredQuantity));
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

    static long findLowestPrice(SortedMap<Integer, Long> ptq, long q) {
        long fulfilled = 0;
        int totalPrice = 0;
        int farmer = 0;
        while (fulfilled != q) {
            int pPayed = (Integer)ptq.keySet().toArray()[farmer];
            long qDemanded = Math.min(q-fulfilled, ptq.get(pPayed));
            fulfilled += qDemanded;
            totalPrice += pPayed*qDemanded;
            farmer++;
        }
        return totalPrice;
    }
}
