package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestOverrideTimeZone extends Setup {

    @Test
    public static void overrideTimeZone() {
        fluentwait.until(d -> {
            try {
                driver.get("https://webbrowsertools.com/timezone/");
                WebElement pretimezone = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"timeZone\"]")));
                System.out.println("pretimezone: " + pretimezone.getText());
                Thread.sleep(5000);

                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();
                devTools.send(Emulation.setTimezoneOverride("EST"));

                driver.get("https://webbrowsertools.com/timezone/");
                WebElement posttimezone = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"timeZone\"]")));
                System.out.println("posttimezone: " + posttimezone.getText());
            } catch (
                    NoAlertPresentException | InterruptedException e) {
            }
            return true;
        });
    }
}
