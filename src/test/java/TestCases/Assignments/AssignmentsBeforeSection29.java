package TestCases.Assignments;

import Utilities.Setup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AssignmentsBeforeSection29 extends Setup {

    //    @Test
    public static void Assignment1() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://www.google.com/");
                        log.info("The navigated url is: " + driver.getCurrentUrl());

                        //search for string
                        xpath = "//*[@id=\"APjFqb\"]";
                        driver.findElement(By.xpath(xpath)).sendKeys("Way2Automation");
                        driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);

                        xpath = "//*[@id=\"recaptcha-anchor\"]";
                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(xpath)));
                        driver.findElement(By.xpath(xpath)).click();
                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();

                        //click the first link
                        xpath = "//*[@id=\"wxVGi\"]/div/div/div/div/div/div/div[1]/div/span/a";
                        driver.findElement(By.xpath(xpath)).click();

                        //get the count of the links
                        List<WebElement> links = driver.findElements(By.tagName("a"));
                        for (WebElement link : links) {
                            String url = link.getAttribute("href");
                            System.out.println(url);
                        }

                    } catch (NoAlertPresentException e) {
                        return true; // Alert has disappeared
                    }
                    return true;
                });


    }

    @Test
    public static void Assignment2() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://qa.way2automation.com/");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        // Locate 'Name' label and field
                        String xpathNameLabel = "//*[@id=\"load_form\"]/fieldset[1]/label";
                        WebElement nameLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathNameLabel)));
                        WebElement nameField = driver.findElement(with(By.tagName("input")).toRightOf(nameLabel));
                        nameField.sendKeys("John");

                        // Locate 'Phone' field
                        WebElement phoneField = driver.findElement(with(By.tagName("input")).below(nameField).below(nameField));
                        phoneField.sendKeys("1234567890");

                        WebElement emailField = driver.findElement(with(By.tagName("input")).below(phoneField));
                        emailField.sendKeys("john.doe@example.com");

                        WebElement countryDropdown = driver.findElement(with(By.tagName("select")).below(emailField));
                        countryDropdown.sendKeys("Bangladesh");

                        WebElement cityField = driver.findElement(with(By.tagName("input")).below(countryDropdown));
                        cityField.sendKeys("Dhaka");

                        WebElement usernameField = driver.findElement(with(By.tagName("input")).below(cityField));
                        usernameField.sendKeys("john_doe");

                        WebElement passwordField = driver.findElement(with(By.tagName("input")).below(usernameField));
                        passwordField.sendKeys("password123");

                        // Submit the form
//                        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"load_form\"]/div[1]/div[2]/input"));
                        WebElement submitButton = driver.findElement(with(By.tagName("input")).below(passwordField));
                        submitButton.click();

                        log.info("Form submitted successfully!");
                        xpath = "//*[@id=\"alertk\"]";
//                        Thread.sleep(1000);
                        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
                        log.info("this is the alert message : " + driver.findElement(By.xpath(xpath)).getText());

                    } catch (NoAlertPresentException e) {
                        return true; // Alert has disappeared
                    }
                    return true;
                });


    }

    @Test
    public static void Assignment3() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://timesofindia.indiatimes.com/poll.cms");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        xpath = "//*[@id=\"mathq2\"]";
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                        String mathQuestion = driver.findElement(By.xpath(xpath)).getText();

                        int result = calculateResult(mathQuestion);
                        xpath = "//*[@id=\"mathusersans2\"]";
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                        driver.findElement(By.xpath(xpath)).sendKeys(String.valueOf(result));
                    } catch (NoAlertPresentException e) {

                    }
                    return true;
                });
    }

    private static int calculateResult(String mathQuestion) {
        String[] parts = mathQuestion.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        String operator = parts[1];
        int num2 = Integer.parseInt(parts[2]);
        int result = 0;
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            result = num1 / num2;
        }
        return result;
    }

    @Test
    public static void Assignment4() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://qa.way2automation.com/");
                        log.info("Navigated to: " + driver.getCurrentUrl());


                        driver.manage().window().setPosition(new Point(0, 0));

                        //set dimension
                        for (int i = 50; i < 120; i++) {
                            driver.manage().window().setSize(new Dimension(10 * (i + 1), 8 * (i + 1)));
//                            System.out.println(i + " " + driver.manage().window().getSize());
                        }

                        Thread.sleep(2000);

                    } catch (NoAlertPresentException e) {
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                });
    }
}