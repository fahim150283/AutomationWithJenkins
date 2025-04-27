package TestCases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestAllureReport2 {

    @Test
    public void S2_TestCase1() {
        System.out.println("S2_Test Case Passed");
    }

    @Test
    public void S2_TestCase2() {
        System.out.println("S2_Test Case Failed");
        Assert.fail("S2_Intentional Failure for demo.");
    }

    @Test
    public void S2_TestCase3() {
        System.out.println("S2_Test Case Skipped");
        throw new SkipException("S2_Intentional Skip for demo.");
    }
}
