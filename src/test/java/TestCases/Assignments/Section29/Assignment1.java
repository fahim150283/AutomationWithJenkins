package TestCases.Assignments.Section29;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class Assignment1 extends Utilities.Setup {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void assignment1() {
        fluentwait.until(d -> {
            try {
                driver.get("https://www.makemytrip.com/");
                xpath = "//*[@id=\"SW\"]/div[3]/div/div/a[1]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));

                //close the registration modal
                xpath = "//*[@id=\"SW\"]/div[1]/div[2]/div[2]/div/section/span";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //round trip
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[1]/ul/li[2]/span";
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

                Thread.sleep(2000);
                //from
                xpath = "//div[@id=\'top-banner\']/div[2]/div/div/div/div/div[2]/div/div";
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
                //search for a place
                xpath = "//div[@id=\'top-banner\']/div[2]/div/div/div/div/div[2]/div/div/div/div/div/div/input";
                driver.findElement(By.xpath(xpath)).sendKeys("new york");
                Thread.sleep(2000);
                //select the first option
                xpath = "//*[@id=\"react-autowhatever-1-section-0-item-0\"]";
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

                //to
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[2]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();
                //search for a place
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div/input";
                driver.findElement(By.xpath(xpath)).sendKeys("london");
                Thread.sleep(2000);
                //select the first option
                xpath = "//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                Thread.sleep(2000);
                //select first date
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div[3]/div[4]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //select second date
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                Thread.sleep(2000);

                //click search
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/p";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //Scroll to load more items
                Thread.sleep(17000);
                xpath = "//*[@id=\"listing-id\"]/div/div[2]/div/div[1]";
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[1]")));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                for (int i = 0; i < 10; i++) {
                    js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
                    Thread.sleep(400);
                }
                js.executeScript("window.scrollTo(0, 0);");

                //get the information of the flights
                xpath = "//div[@data-test=\"component-clusterItem\"]";
                List<WebElement> flights = driver.findElements(By.xpath(xpath));
                int serialNumber = 0;
                for (int i = 0; i < flights.size(); i++) {
                    if ((i + 1) % 6 == 0) {
                        //ignore
                    } else {
                        serialNumber++;
                        String flightName = driver.findElement(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[" + (i + 1) + "]/div[1]/div[3]/div[1]/div/div/div/p[1]/div")).getText();
                        String price = driver.findElement(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[" + (i + 1) + "]/div[1]/div[3]/div[2]/div/div/div")).getText().replaceAll("[^0-9]", "");
                        System.out.println(serialNumber + ". " + flightName + " - " + price + " BDT");
                    }
                }


            } catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
        softAssert.assertAll();
    }
}
