package TestCases.Assignments.Section29;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Assignment2 extends Setup {

    @Test
    public void RandomlyCheckingCheckboxes() throws InterruptedException {
        driver.get("https://proleed.academy/exercises/selenium/automation-practice-form-with-radio-button-check-boxes-and-drop-down.php");

        WebElement chkbx1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passport")));
        WebElement chkbx2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("studentid")));
        WebElement chkbx3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("votercard")));
        WebElement chkbx4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("drivinglicense")));

        scrollToElementWithActions(chkbx1);

        int random1 = (int) (Math.random() * 4) + 1;
        int random2 = (int) (Math.random() * 4) + 1;

        do {
            random2 = (int) (Math.random() * 4) + 1;
            if (random2 != random1) {
                break;
            }
        } while (random1 == random2);


        switch (random1) {
            case 1:
                chkbx1.click();
                break;
            case 2:
                chkbx2.click();
                break;
            case 3:
                chkbx3.click();
                break;
            case 4:
                chkbx4.click();
                break;
        }


        switch (random2) {
            case 1:
                chkbx1.click();
                break;
            case 2:
                chkbx2.click();
                break;
            case 3:
                chkbx3.click();
                break;
            case 4:
                chkbx4.click();
                break;
        }
    }


    public static void scrollToElementWithActions(WebElement element) {
        new Actions(driver)
                .scrollToElement(element)
                .perform();
    }
}
