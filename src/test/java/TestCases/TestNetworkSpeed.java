package TestCases;

import Utilities.Setup;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.ConnectionType;
import org.testng.annotations.Test;

import java.util.Optional;

public class TestNetworkSpeed extends Setup {

    @Test
    public static void networkSpeed() {
        fluentwait.until(
                d -> {
                    try {
                        DevTools devTools = ((ChromeDriver) driver).getDevTools();
                        devTools.createSession();


                        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
                        devTools.send(Network.emulateNetworkConditions(
                                false,
                                1000,   //Latency in miliseconds
                                200000, // Download throughput (200,000 bps or 200 Kbps)
                                100000, // Upload throughput (100,000 bps or 100 Kbps)
                                Optional.of(ConnectionType.ETHERNET),
                                Optional.empty(),
                                Optional.empty(),
                                Optional.empty()));

                        // navigate to google
                        driver.get("https://onepagelove.com/tag/big-images");
                        log.info("Navigated to: " + driver.getCurrentUrl());
                    } catch (NoAlertPresentException e) {
                    }
                    return true;
                }
        );
    }
}
