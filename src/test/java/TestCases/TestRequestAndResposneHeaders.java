package TestCases;

import Utilities.Setup;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestRequestAndResposneHeaders extends Setup {

    @Test
    public void testRequestAndResponseHeaders() {
        fluentwait.until(d -> {
            try {
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

                devTools.addListener(Network.requestWillBeSent(), request -> {
                    Headers header = request.getRequest().getHeaders();
                    if (!header.isEmpty()) {
                        System.out.println();
                        System.out.println("********************                 Request Headers                 ********************");
                        System.out.println();
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                });

                devTools.addListener(Network.responseReceived(), response -> {
                    Headers header = response.getResponse().getHeaders();
                    if (!header.isEmpty()) {
                        System.out.println();
                        System.out.println("********************                 Response Headers                 ********************");
                        System.out.println();
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                });

                driver.get("https://flipkart.com");

            } catch (Exception e) {
            }
            return true;
        });
    }

    @Test
    public void testRequestAndResponseHeadersUrlAndStatus() {
        fluentwait.until(d -> {
            try {
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

                devTools.addListener(Network.requestWillBeSent(), request -> {
                    Headers header = request.getRequest().getHeaders();
                    if (!header.isEmpty()) {
                        System.out.println("********************                 Request Headers                 ********************");
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                });

                devTools.addListener(Network.responseReceived(), response -> {
                    Headers header = response.getResponse().getHeaders();
                    if (!header.isEmpty()) {
                        System.out.println("********************                 Response Headers                 ********************");
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                    System.out.println("Response URL is : " + response.getResponse().getUrl() + "  status code is : " + response.getResponse().getStatus());
                });

                driver.get("https://flipkart.com");
            } catch (Exception e) {
            }
            return true;
        });
    }


    @Test
    public void testRequestAndResponseHeadersCustomHeaders() {
        fluentwait.until(d -> {
            try {

                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();

                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

                devTools.addListener(Network.requestWillBeSent(), request -> {
                    Headers header = request.getRequest().getHeaders();
                    if (!header.isEmpty()) {
                        System.out.println("********************                 Request Headers                 ********************");
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                });

                devTools.addListener(Network.responseReceived(), response -> {
                    Headers header = response.getResponse().getHeaders();
                    if (!header.isEmpty()) {
                        System.out.println("********************                 Response Headers                 ********************");
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                    System.out.println("Response URL is : " + response.getResponse().getUrl() + "  status code is : " + response.getResponse().getStatus());
                });

                Map<String, Object> headers = new HashMap<String, Object>();
                headers.put("This is a customHeaderName", "This is a customHeaderValue");
                headers.put("Fahim", "The one and only Automation Tester");
                Headers head = new Headers(headers);
                devTools.send(Network.setExtraHTTPHeaders(head));

                driver.get("https://flipkart.com");
                Thread.sleep(1500);

            } catch (Exception e) {
            }
            return true;
        });
    }
}