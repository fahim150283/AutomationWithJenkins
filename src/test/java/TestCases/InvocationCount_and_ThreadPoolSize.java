package TestCases;

import Utilities.Setup;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InvocationCount_and_ThreadPoolSize extends Setup {
    SoftAssert softAssert = new SoftAssert();


    @Test(
//            invocationCount = 10, threadPoolSize = 4
    )
    public void InvocationCount_and_ThreadPoolSize() {
//        ThreadLocal_driver.get().get("https://www.google.com/");
        driver.get("https://www.google.com/");
        softAssert.assertEquals(driver.getTitle(), "Google");
        System.out.println("lare lappa");
//        softAssert.assertAll();
    }
}