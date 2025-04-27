package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.Optional;

public class TestMOCGeoLocation extends Setup {

    @Test
    public static void TestMOCGeoLocation() throws InterruptedException {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();


        devTools.send(Emulation.setGeolocationOverride(Optional.of(21.470754377411506), Optional.of(-158.2180186190138), Optional.of(100)));

        driver.get("https://www.gps-coordinates.net/my-location");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addr\"]")));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[2]/div[2]/div[2]/p")).getText());
    }
}
