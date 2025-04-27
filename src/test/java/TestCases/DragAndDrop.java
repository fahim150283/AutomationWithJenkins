package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DragAndDrop extends Setup {

    @Test
    public static void dragAndDrop() throws InterruptedException {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://jqueryui.com/droppable/");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        List<WebElement> frame1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
                        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("demo-frame")));
                        driver.switchTo().frame(frame);
                        WebElement draggable = driver.findElement(By.id("draggable"));
                        WebElement droppable = driver.findElement(By.id("droppable"));
                        wait.until(ExpectedConditions.elementToBeClickable(draggable));
                        wait.until(ExpectedConditions.elementToBeClickable(droppable));

                        Actions action = new Actions(driver);
                        action.dragAndDrop(draggable, droppable).perform();

                        Thread.sleep(2000);

                    } catch (NoAlertPresentException | InterruptedException e) {
                    }
                    return true;
                });
    }
}
