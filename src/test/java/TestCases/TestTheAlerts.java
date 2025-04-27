package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestTheAlerts extends Setup {

    @Test
    public static void AlertAccept() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        login.sendKeys("abc");
//                        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc");
                        //click the signin button
                        driver.findElement(By.xpath("//input[@title='Sign in']")).click();
                        wait.until(ExpectedConditions.alertIsPresent());
                        driver.switchTo().alert().accept();

                    } catch (NoAlertPresentException e) {
                    }
                    return true;
                }
        );
    }

    @Test
    public static void AlertDismiss() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        login.sendKeys("abc");
//                        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc");
                        //click the signin button
                        driver.findElement(By.xpath("//input[@title='Sign in']")).click();
                        wait.until(ExpectedConditions.alertIsPresent());
                        driver.switchTo().alert().dismiss();

                    } catch (NoAlertPresentException e) {
                    }
                    return true;
                }
        );
    }

    @Test
    public static void AlertTextReceive() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        login.sendKeys("asbc");
//                        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("asbc");
                        //click the signin button
                        driver.findElement(By.xpath("//input[@title='Sign in']")).click();
                        wait.until(ExpectedConditions.alertIsPresent());
                        System.out.println(driver.switchTo().alert().getText() + " is the alert message");

                    } catch (NoAlertPresentException e) {
                    }
                    return true;
                }
        );
    }
}
