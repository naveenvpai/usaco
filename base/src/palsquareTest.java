import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Naveen on 2/8/17.
 */
public class palsquareTest {
    @DataProvider(name = "ispal")
    public Object[][] palData() {
        return new Object[][] {
                {"12321", true},
                {"1221", true},
                {"3233", false},
                {"32455", false},
                {"1", true}
        };
    }

    @DataProvider(name = "represent")
    public Object[][] representData() {
        return new Object[][] {
                {10, 2, "1010"},
                {10, 10, "10"},
                {10, 3, "101"},
                {100, 20, "50"},
                {16, 20, "G"}
        };
    }

    @Test(dataProvider = "ispal")
    public void testIsPalindrome(String number, boolean result) throws Exception {
        assertEquals(palsquare.isPalindrome(number), result);
    }

    @Test(dataProvider = "represent")
    public void testRepresentBaseB(int num, int base, String result) {
        assertEquals(palsquare.representBaseB(num, base), result);
    }

}