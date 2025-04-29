package TestCases.SimpleCases;

import Utilities.Setup;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCases1 extends Setup {
    @Test
    public void testPass() {
        System.out.println("Executing Pass Test");
        Assert.assertTrue(true);
    }

//    @Test
    public void testFail() {
        driver.get("https://google.com");
        System.out.println("Executing Fail Test");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.fail("Intentional Failure for demo.");
    }

    @Test
    public void testSkip() {
        System.out.println("Executing Skip Test");
        throw new SkipException("Intentional Skip for demo.");
    }
}
