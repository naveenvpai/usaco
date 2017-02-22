import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

/**
 * Created by Naveen on 2/21/17.
 */
public class skidesignTest {

    @DataProvider(name = "bottom")
    public Object[][] bottomProvider() {
        return new Object[][]{
                {new int[]{3,2,2,4,3}, new int[]{2,2,3,4,3}},
                {new int[]{2,2,3,4,3}, new int[]{2,2,3,4,3}}
        };
    }

    @DataProvider(name = "top")
    public Object[][] topProvider() {
        return new Object[][]{
                {new int[]{3,3,3,3,2}, new int[]{2,3,3,3,3}},
                {new int[]{2,2,3,4,5}, new int[]{2,2,3,4,5}}
        };
    }

    @Test(dataProvider = "bottom")
    public void testCleanUpBottom(int[] before, int[] after) throws Exception {
        skidesign.cleanUpBottom(before);
        assertTrue(Arrays.equals(before,after));
    }

    @Test(dataProvider = "top")
    public void testCleanUpTop(int[] before, int[] after) throws Exception {
        skidesign.cleanUpTop(before);
        assertTrue(Arrays.equals(before,after));
    }

}