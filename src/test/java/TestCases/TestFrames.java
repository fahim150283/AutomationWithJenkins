package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class TestFrames extends Setup {


    @Test
    public static void Frames() {
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_frames");
        List<WebElement> frame1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
//         System.out.println(frame.size());

        //click the try it button
        driver.switchTo().frame("iframeResult");
        xpath = "/html/body/button";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        System.out.println("Clicked on the button");
        System.out.println(driver.findElements(By.tagName("iframe")).size());

        // Switch back to the main page before switching to another frame
        driver.switchTo().parentFrame();

        //get text of a frame
        driver.switchTo().frame(1);
        xpath = "//*[@id=\"main\"]/h1";
        WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        System.out.println("Text of the frame is: " + text.getText());
    }
}
