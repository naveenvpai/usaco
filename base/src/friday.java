/*
ID: pai.nav1
LANG: JAVA
TASK: friday
*/

import java.io.*;

public class friday {
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f = new BufferedReader(new FileReader("friday.in"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
                int period = Integer.parseInt(f.readLine());
                int startYear = 1900;
                int day = 13;
                int[] numThirteenthDays = new int[7]; //0 corresponds to Saturday
                for (int year = startYear; year < startYear+period; year++) {
                    int[] months = getDaysInMonths(year);
                    for (int month : months) {
                        numThirteenthDays[dayOfTheWeek(day)]++;
                        day += month;
                    }
                }
                for (int i = 0; i < numThirteenthDays.length; i++) {
                    if (i!=0) out.print(" ");
                    out.print(numThirteenthDays[i]);
                }
                out.print("\n");
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isLeapYear(int year) {
        return year%4==0 && (year%100!=0 || year%400==0);
    }

    private static int[] getDaysInMonths(int year) {
        int[] result = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if (isLeapYear(year)) result[1] = 29;
        return result;
    }

    private static int dayOfTheWeek(int day) {
        return (day+1)%7;
    }
}

