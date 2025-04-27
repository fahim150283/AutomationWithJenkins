package TestCases.Assignments.Section29;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assignment3 extends Setup {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void assignment3() {
        driver.get("https://www.amazon.in/");
        WebElement searchbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchbar.sendKeys("iphone 14 pro max");

        //click the first suggestion
        xpath = "//*[@id=\"sac-suggestion-row-1\"]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

        //click the first result
        xpath = "//*[@data-index=\"5\"]//div/div/span/div/div/div/div[2]/div/div/div[1]/a";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

        //copy the name and price of the first result
        xpath = "//*[@id=\"productTitle\"]";
        String name = driver.findElement(By.xpath(xpath)).getText();
        System.out.println("name is: " + name);
        xpath = "//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[2]/span[2]/span[2]";
        String price = driver.findElement(By.xpath(xpath)).getText().replace("₹", "").replace("$", "").replace(",", "").replace(".", "");
        xpath = "//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[3]/span[2]/span[3]";
        price += driver.findElement(By.xpath(xpath)).getText().replace("₹", "").replace("$", "").replace(",", "").replace(".", "");
        System.out.println("price is: " + price);

        //click the add to cart button
        xpath = "//*[@id=\"add-to-cart-button\"]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

        //click the view cart button
        xpath = "//*[@id=\"sw-gtc\"]/span/a";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

        //get the name and price of the first item in the cart
        xpath = "//*[class=\"sc-list-item-content\"]/div[4]/div/div[2]/ul/li/span/a/span[1]/h4/span/span[2]";
        String cartName = driver.findElement(By.xpath(xpath)).getText().replace("...", "");
        System.out.println("cart name is: " + cartName);
        xpath = "//div[4]/div/div[2]/ul/div[1]/div/div/div/span/span/span[2]";
        String cartPrice = driver.findElement(By.xpath(xpath)).getText().replace("$", "").replace(",", "").replace(".", "");
        System.out.println("cart price is: " + cartPrice);

        //compare the name and price of the first result with the name and price of the first item in the cart
        boolean nameMatch = name.contains(cartName);
        softAssert.assertTrue(nameMatch, "name is not the same");
        softAssert.assertEquals(price, cartPrice, "price is not the same");
        softAssert.assertAll();
    }
}