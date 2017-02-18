import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Naveen on 2/17/17.
 */
public class crypt1Test {
    @DataProvider(name = "allValid")
    public Object[][] allValidProvider() {
        return new Object[][] {
                {"1234", new String[]{"2412","3232","2354"}, false},
                {"1234", new String[]{"2412","3232","2344"}, true}
        };
    }

    @Test(dataProvider = "allValid")
    public void testAllValid(String valid, String[] test, boolean value) {
        assertEquals(crypt1.allValid(valid,test), value);
    }

    @DataProvider(name = "cryptSolved")
    public Object[][] cryptSolvedProvider() {
        return new Object[][] {
                {"23468", "222", "22", true},
                {"23468", "322", "22", false}
        };
    }

    @Test(dataProvider = "cryptSolved")
    public void testCryptSolved(String valid, String threeDigit, String twoDigit, boolean value) {
        assertEquals(crypt1.cryptSolved(threeDigit, twoDigit, valid), value);
    }
}