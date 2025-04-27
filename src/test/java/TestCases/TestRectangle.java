package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestRectangle extends Setup {


    @Test
    public static void Rectangle() {
        driver.get("https://github.com/");
        WebElement img = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Homepage']//*[name()='svg']")));


        Rectangle rect = img.getRect();

        System.out.println("Height : " + rect.getHeight());
        System.out.println("Width : " + rect.getWidth());
        System.out.println("X Coord : " + rect.getX());
        System.out.println("Y Coord : " + rect.getY());
    }
}
