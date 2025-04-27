package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.security.Security;
import org.testng.annotations.Test;

public class TestBadSSL extends Setup {

    @Test
    public static void TestBadSSL() throws InterruptedException {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();


        devTools.send(Security.enable());
        //This enables the page to be visible even if the page can not be seen followed by this error "Your connection is not private"
        devTools.send(Security.setIgnoreCertificateErrors(true));


        driver.get("https://expired.badssl.com");
        Thread.sleep(2000);
        xpath = "//*[@id=\"content\"]/h1";
        System.out.println(driver.findElement(By.xpath(xpath)).getText());
    }
}
