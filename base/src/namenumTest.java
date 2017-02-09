import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Naveen on 2/8/17.
 */
public class namenumTest {

    @Test
    public void testGetSerialNumber() {
        assertEquals(namenum.getSerialNumber("GREG"), "4734");
        assertEquals(namenum.getSerialNumber("NAVEEN"), "628336");
        assertEquals(namenum.getSerialNumber("KENT"), "5368");
    }
}