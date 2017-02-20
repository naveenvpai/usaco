/*
ID: pai.nav1
LANG: JAVA
TASK: wormhole
*/

import java.awt.*;
import java.io.*;
import java.util.*;

/*
Parse.
Given n points, find every distinct pairing of points so that at least one pair has the same y-coordinates

Contextualize.
n = 2, 4, 6, 8, 10, 12
x,y in [0, 1,000,000] so lets try ints

Solution ideas.
Find all the Points with same y-value then figure out how many distinct pairings result in these paired

Errors.
So many. Will go back and reflect after my victory dance.

*/

public class wormhole {
    static int n;
    static Point[] points;
    //memoization boi!!!!!!
    static HashMap<Point, Point> adjacentRight = new HashMap<>();
    public static void main(String[] args) {
        try {
            try {
                BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

                n = Integer.valueOf(f.readLine());
                points = new Point[n];
                for (int i = 0; i < n; i++) {
                    String[] pointRep = f.readLine().split(" ");
                    points[i] = new Point(Integer.valueOf(pointRep[0]), Integer.valueOf(pointRep[1]));
                }

                ArrayList<ArrayList<Integer>> allPairings = pairingsIncluding(firstInts(), new ArrayList());
                int validCount = 0;
                for (ArrayList pairing : allPairings) {
                    if (validPairing(pairing)) validCount++;
                }

                out.println(validCount);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Integer> firstInts() {
        ArrayList<Integer> retVal = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            retVal.add(i);
        }
        return retVal;
    }

    static ArrayList<ArrayList<Integer>> pairingsIncluding(ArrayList<Integer> remaining, ArrayList<Integer> parentList) {
        ArrayList<ArrayList<Integer>> retVal = new ArrayList<>();
        if (remaining.size() == 2) {
            parentList.addAll(remaining);
            retVal.add(parentList);
            return retVal;
        }

        for (int i = 1; i < remaining.size(); i++) {
            ArrayList<Integer> newRemaining = new ArrayList<>(remaining);
            ArrayList<Integer> newParentList = new ArrayList<>(parentList);
            newParentList.add(newRemaining.remove(0));
            newParentList.add(newRemaining.remove(i - 1));
            retVal.addAll(pairingsIncluding(newRemaining, newParentList));
        }
        return retVal;
    }

    static String pairString(int x, int y) {
        return x + "-" + y;
    }

    static boolean validPairing(ArrayList<Integer> pairing) {
        HashMap<Point, Point> pointMap = new HashMap<>();
        for (int i = 0; i < pairing.size() - 1; i+=2) {
            Point first = points[pairing.get(i)];
            Point second = points[pairing.get(i+1)];
            pointMap.put(first, second);
            pointMap.put(second, first);
        }
        for (int i = 0; i < n; i++) {
            Point currPoint = points[i];
            boolean lastWasWormhole = false;
            while (true) {
                Point nextPoint = lastWasWormhole ? adjacentRight(currPoint) : pointMap.get(currPoint);
                lastWasWormhole = !lastWasWormhole;
                //Betsy's going to infinity
                if (nextPoint == null) break;
                if (nextPoint.equals(points[i]) && !lastWasWormhole) return true;
                currPoint = nextPoint;
            }
        }
        return false;
    }

    static boolean pointInX(int x1, int x2) {
        int left = Math.min(x1, x2);
        int right = Math.max(x1, x2);
        for (Point p : points) {
            if (p.x > left && p.x < right) return true;
        }
        return false;
    }

    static Point adjacentRight(Point origin) {
        if (adjacentRight.containsKey(origin)) return adjacentRight.get(origin);
        Point retVal = null;
        for (Point p : points) {
            if (origin.y == p.y && p.x > origin.x) {
                if (retVal == null) {
                    retVal = p;
                } else {
                    if (p.x < retVal.x) retVal = p;
                }
            }
        }
        adjacentRight.put(origin, retVal);
        return retVal;
    }
}
