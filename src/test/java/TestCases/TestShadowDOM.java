package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestShadowDOM extends Setup {

    @Test
    public static void testShadowDOM() {
        driver.get("chrome://downloads/");

        WebElement root = driver.findElement(By.cssSelector("downloads-manager"));

        WebElement shadowRoot1 = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root);

        assert shadowRoot1 != null;
        WebElement root2 = shadowRoot1.findElement(By.cssSelector("downloads-toolbar"));
        WebElement shadowRoot2 = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root2);

        assert shadowRoot2 != null;
        WebElement root3 = shadowRoot2.findElement(By.cssSelector("cr-toolbar"));
        WebElement shadowRoot3 = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root3);


        assert shadowRoot3 != null;
        WebElement root4 = shadowRoot3.findElement(By.cssSelector("cr-toolbar-search-field"));
        WebElement shadowRoot4 = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root4);

        shadowRoot4.findElement(By.cssSelector("#searchInput")).sendKeys("Hello");
    }
}
