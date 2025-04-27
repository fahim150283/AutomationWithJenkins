package TestCases;

import Utilities.Setup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.console.Console;
import org.openqa.selenium.devtools.v129.log.Log;
import org.testng.annotations.Test;

public class TestConsoleLogs extends Setup {

    @Test
    public static void ConsoleLogs() {
        fluentwait.until(d -> {
            try {
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();


                devTools.send(Log.enable());
                devTools.send(Console.enable());

                devTools.addListener(Log.entryAdded(), entry -> {


                    System.out.println("Text is : " + entry.getText());
                    System.out.println("TimeStamp is : " + entry.getTimestamp());
                    System.out.println("Source is : " + entry.getSource());
                    System.out.println("Level is : " + entry.getLevel());
                });

                devTools.addListener(Console.messageAdded(), message -> {
                    System.out.println("Console Text is : " + message.getText());
                });

                driver.get("http://flipkart.com");

                ((JavascriptExecutor) driver).executeScript("console.log('This is a sample log')");

            } catch (NoAlertPresentException e) {
            }
            return true;
        });
    }
}
