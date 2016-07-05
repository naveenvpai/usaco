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
                System.out.println(System.getProperty("user.dir"));
                BufferedReader f    = new BufferedReader(new FileReader("gift1.in"));
                PrintWriter out     = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

                int numFriends = Integer.parseInt(f.readLine());
                String[] friendList = new String[numFriends];
                HashMap<String, Integer> indexMap = new HashMap<String, Integer>(numFriends);
                int[] initialBalance = new int[numFriends];
                HashMap<String, Integer> accounts = new HashMap<String, Integer>(numFriends);
                for (int i = 0; i < numFriends; i++) {
                    friendList[i] = f.readLine();
                    accounts.put(friendList[i], 0);
                    indexMap.put(friendList[i], i);
                }
                for (int i = 0; i < numFriends; i++) {
                    String currFriend = f.readLine();
                    StringTokenizer st = new StringTokenizer(f.readLine());
                    int amt = Integer.parseInt(st.nextToken());
                    initialBalance[indexMap.get(currFriend)] = amt;
                    int numRecip = Integer.parseInt(st.nextToken());
                    if (numRecip == 0) continue;
                    int balance = accounts.get(currFriend) + amt%numRecip;
                    accounts.put(currFriend, balance);
                    int gift = amt/numRecip;
                    for (int j = 0; j < numRecip; j++) {
                        String recipient = f.readLine();
                        int prevBalance = accounts.get(recipient);
                        accounts.put(recipient, prevBalance+gift);
                    }
                }
                for (int i = 0; i < numFriends; i++) {
                    out.print(friendList[i]+" "+Integer.valueOf(accounts.get(friendList[i])-initialBalance[i]).toString()+"\n");
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
