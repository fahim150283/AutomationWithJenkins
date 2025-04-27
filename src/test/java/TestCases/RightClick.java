package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RightClick extends Setup {

    @Test
    public static void RightClick() throws InterruptedException {

        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://deluxe-menu.com/popup-mode-sample.html");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        WebElement rightClickButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[1]/tbody/tr/td[3]/p[2]/img")));

                        Actions action = new Actions(driver);
                        action.contextClick(rightClickButton).pause(100).perform();
                        // Hover over "Product Info"
                        WebElement productInfo = driver.findElement(By.xpath("//*[@id=\"dm2m1tbl\"]/tbody/tr[2]"));
                        action.moveToElement(productInfo).pause(500).perform();
                        // Hover over "Installation"
                        WebElement installation = driver.findElement(By.xpath("//*[@id=\"dm2m2tbl\"]/tbody/tr[2]"));
                        action.moveToElement(installation).pause(500).perform();
                        // Click How To Set up
                        WebElement setup = driver.findElement(By.xpath("//*[@id=\"dm2m3tbl\"]/tbody/tr[2]"));
                        setup.click();

                        Thread.sleep(5000);
                    } catch (NoAlertPresentException | InterruptedException e) {
                    }
                    return true;
                });
    }
}