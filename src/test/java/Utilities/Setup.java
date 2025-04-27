package Utilities;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import java.time.Duration;

import static org.testng.log4testng.Logger.getLogger;

public class Setup {

    public static Logger log;

    static {
        try {
            log = getLogger(Class.forName(Setup.class.getName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver driver;
    //    public static ThreadLocal<WebDriver> ThreadLocal_driver = new ThreadLocal<>();
    static String browser;

    //variables for ease of code
    public static String xpath;
    public static String id;
    public static String cssSelector;
    public static Wait<WebDriver> fluentwait;
    public static WebDriverWait wait;

    private WebDriver getDriver(String browser, String headless) {
        System.out.println("✅ Selected Browser: " + browser); // Debugging output
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless"); // Enable headless mode
                options.addArguments("--disable-gpu"); // Disable GPU for headless mode
            }
            options.addArguments("--disable-cookies"); // Disable cookies
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless"); // Enable headless mode
                options.addArguments("--disable-gpu"); // Disable GPU for headless mode
            }
            options.addArguments("--disable-cookies"); // Disable cookies
            driver = new ChromeDriver(options);
        }

        return driver;
    }

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") String headless) {
        driver = getDriver(browser, headless);
//        ThreadLocal_driver.set(driver);

        //waits
        {
            // implicit
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            // explicit
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Fluent
            fluentwait =
                    new FluentWait<>(driver)
                            .withTimeout(Duration.ofSeconds(5))
                            .pollingEvery(Duration.ofMillis(900))
                            .ignoring(NoAlertPresentException.class);
        }

        driver.manage().window().setPosition(new Point(-1000, 0));
        driver.manage().window().maximize();
//        driver.manage().window().minimize();
    }

    @AfterMethod
    public void afterTest() throws InterruptedException {
//        Thread.sleep(2000);
// close all the browsers
//        if (ThreadLocal_driver.get() != null) {
//            ThreadLocal_driver.get().close();
//        }
        if (driver != null) {
            driver.quit();
        }
    }

}