package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class TestTabsAndPopUps extends Setup {

    @Test
    public void testTabsAndPopUps() throws InterruptedException {
        fluentwait.until(
                d -> {
                    driver.get("https://hdfcbank.com/");
                    log.info("Navigated to: " + driver.getCurrentUrl());
                    Actions action = new Actions(driver);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"custom-button\"]/div/div/div/button")));
                    action.moveToElement(driver.findElement(By.xpath("//*[@id=\"custom-button\"]/div/div/div/button"))).click().perform();

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main\"]/div[2]/div[11]/div[3]/div/div/div/div/div/div[1]/div/div[1]/a")));
                    driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[11]/div[3]/div/div/div/div/div/div[1]/div/div[1]/a")).click();

                    Set<String> windows = driver.getWindowHandles();
                    Iterator<String> iterator = windows.iterator();

                    String[] windowArray = new String[windows.size()];
                    for (int i = 0; i < windows.size(); i++) {
                        windowArray[i] = iterator.next();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    driver.switchTo().window(windowArray[0]);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    driver.switchTo().window(windowArray[1]);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                });

    }
}
