package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestBrowserOptions {

    @Test
    public static void BrowserOptions() {
        Map<String, String> mobileEm = new HashMap<String, String>();
        mobileEm.put("deviceName", "iPhone X");

        ChromeOptions opt = new ChromeOptions();
        //opt.addArguments("--headless");
        opt.setAcceptInsecureCerts(true);
        //opt.addArguments("disable-infobars");
        //opt.addArguments("window-size=1400,1000");
        opt.addArguments("incognito");

        opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        opt.setExperimentalOption("mobileEmulation", mobileEm);

        WebDriver driver = new ChromeDriver(opt);
        driver.get("https://selenium.dev/");

        System.out.println(driver.getTitle());

        driver.quit();
    }
}
