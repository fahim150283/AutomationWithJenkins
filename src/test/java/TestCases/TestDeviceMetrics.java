package TestCases;

import Utilities.Setup;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.emulation.Emulation;
import org.testng.annotations.Test;

import java.util.Optional;

public class TestDeviceMetrics extends Setup {

    @Test
    public static void TestDeviceMetrics() throws InterruptedException {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setDeviceMetricsOverride(
                480,  // Width
                540,  // Height
                50,   // Scale (Default: 100%)
                true,  // Mobile mode enabled
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        driver.get("https://youtube.com");
    }
}
