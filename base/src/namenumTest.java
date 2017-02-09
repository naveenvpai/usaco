import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Naveen on 2/8/17.
 */
public class namenumTest {

    @DataProvider(name = "serial")
    public Object[][] serialNumberProvider() {
        return new Object[][] {
                { "GREG", "4734" },
                { "NAVEEN", "628336" },
                { "KENT", "5368" }
        };
    }

    @Test(dataProvider = "serial")
    public void testGetSerialNumber(String name, String serial) {
        assertEquals(namenum.getSerialNumber(name), serial);
    }
}