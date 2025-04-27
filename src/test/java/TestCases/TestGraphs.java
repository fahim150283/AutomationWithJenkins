package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class TestGraphs extends Setup {

    @Test
    public static void testGraphs() {
        driver.get("https://canvasjs.com/javascript-charts/chart-image-overlay/");
        log.info("Navigated to: " + driver.getCurrentUrl());

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"preview0\"]")));
        driver.switchTo().frame(iframe);
        Actions action = new Actions(driver);
        xpath = "//*[@id=\"chartContainer\"]/div/img";

        List<WebElement> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        System.out.println(webElements.size());
        for (int i = 0; i < webElements.size(); i++) {
            System.out.println("xpath is: " + xpath + "[" + (i + 1) + "]");
            action.moveToElement(driver.findElement(By.xpath(xpath + "[" + (i + 1) + "]"))).click().perform();
            System.out.println(driver.findElement(By.xpath("//*[@id=\"chartContainer\"]/div/div[2]/div[1]")).getText());
            System.out.println("############################################################################################################");
        }
    }
}
