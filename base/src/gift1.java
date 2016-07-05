/*
ID: pai.nav1
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class gift1 {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f    = new BufferedReader(new FileReader("gift1.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

                int numFriends = Integer.parseInt(f.readLine());
                String[] friendList = new String[numFriends];
                HashMap<String, Integer> profits = new HashMap<String, Integer>(numFriends);
                for (int i = 0; i < numFriends; i++) {
                    friendList[i] = f.readLine();
                    profits.put(friendList[i], 0);
                }
                for (int i = 0; i < numFriends; i++) {
                    String currFriend = f.readLine();
                    StringTokenizer st = new StringTokenizer(f.readLine());
                    int amt = Integer.parseInt(st.nextToken());
                    int numRecip = Integer.parseInt(st.nextToken());
                    if (numRecip == 0) continue;
                    int balance = profits.get(currFriend) + amt%numRecip - amt;
                    profits.put(currFriend, balance);
                    int gift = amt/numRecip;
                    for (int j = 0; j < numRecip; j++) {
                        String recipient = f.readLine();
                        int prevBalance = profits.get(recipient);
                        profits.put(recipient, prevBalance+gift);
                    }
                }
                for (int i = 0; i < numFriends; i++) {
                    out.print(friendList[i]+" "+Integer.valueOf(profits.get(friendList[i])).toString()+"\n");
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
}
