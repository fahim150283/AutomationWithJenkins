package TestCases.TestListenerSample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import static Utilities.Setup.log;

public class MyListener implements WebDriverListener {


    public void afterClick(WebElement element) {

        System.out.println("Clicking on an Element : " + element);
        log.info("Clicking on an Element : " + element);

    }


    public void afterQuit(WebDriver driver) {

        System.out.println("Test execution completed");
        log.info("Test execution completed");

    }

    public void afterGet(WebDriver driver, String url) {


        System.out.println("Navigated to : " + url);
        log.info("Navigated to : " + url);
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {


        System.out.println("Typing in an Element : " + element + " entered the value as : ");
        log.info("Typing in an Element : " + element + " entered the value as : ");

        for (int i = 0; i < keysToSend.length; i++) {

            System.out.println(keysToSend[i] + " ");
            log.info(keysToSend[i] + " ");
        }


    }


}
