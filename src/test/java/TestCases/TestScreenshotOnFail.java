package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestScreenshotOnFail extends Setup {

    @Test
    public void testScreenshotOnFail() {
        try {
            driver.get("https://www.google.com/");
            driver.findElement(By.name("qa")).sendKeys("selenium");
            driver.findElement(By.name("btnK")).click();
        } catch (Exception e) {
//            try {
//                TestScreenshotUsingAshot.sshot("testScreenshotOnFail");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
        }
    }
}
