package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestBasicAuth extends Setup {

    @Test
    public static void BasicAuth() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        xpath = "//h3[normalize-space()='Basic Auth']";
        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText());
    }

    @Test
    public static void BasicAuth1() {
        ((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin"));
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        xpath = "//h3[normalize-space()='Basic Auth']";
        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText());
    }
}
