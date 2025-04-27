package TestCases;

import Utilities.Setup;
import com.google.common.collect.ImmutableList;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.testng.annotations.Test;

import java.util.Optional;

public class TestBlockingNetworkRequest extends Setup {

    @Test
    public static void BlockingNetworkRequest() throws InterruptedException {
        fluentwait.until(
                d -> {
                    try {
                        DevTools devTools = ((ChromeDriver) driver).getDevTools();
                        devTools.createSession();
                        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
                        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.png", "*.jpeg")));

                        // navigate to google
                        driver.get("https://onepagelove.com/tag/big-images");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        Thread.sleep(5000);
                    } catch (NoAlertPresentException | InterruptedException e) {
                    }
                    return true;
                }
        );
    }
}
