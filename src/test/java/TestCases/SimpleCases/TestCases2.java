package TestCases.SimpleCases;

import Utilities.Setup;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCases2 extends Setup {
    @Test
    public void testPass2() {
        System.out.println("Executing Pass Test");
        Assert.assertTrue(true);
    }

    @Test
    public void testFail2() {
        driver.get("https://youtube.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.fail("Intentional Failure for demo");
    }

    @Test
    public void testSkip3() {
        System.out.println("Executing Skip Test");
        throw new SkipException("Intentional Skip for demo.");
    }
}
