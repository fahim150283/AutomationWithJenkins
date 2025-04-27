package TestCases;

import Utilities.Setup;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.performance.Performance;
import org.openqa.selenium.devtools.v127.performance.model.Metric;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class TestPerformanceMonitor extends Setup {

    @Test
    public void testPerformanceMonitor() throws InterruptedException {
        fluentwait.until(
                d -> {
                    try {
                        DevTools devTools = ((ChromeDriver) driver).getDevTools();
                        devTools.createSession();
                        devTools.send(Performance.enable(Optional.of(Performance.EnableTimeDomain.TIMETICKS)));
                        driver.get("https://gb-storefront-8r4z.vercel.app/");
                        Thread.sleep(5000);

                        List<Metric> metrics = devTools.send(Performance.getMetrics());

                        metrics.forEach(metric ->
                                log.info("Metric name is : " + metric.getName() + " and the Value is : " + metric.getValue())
                        );
                    } catch (NoAlertPresentException | InterruptedException e) {
                    }
                    return true;
                });
    }
}
