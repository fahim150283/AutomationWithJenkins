package TestCases;


import Utilities.Setup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Print_to_PDF extends Setup {

    @Test
    public static void Print_to_PDF() throws Exception {

        driver.get("https://selenium.dev/");
        Thread.sleep(2000);


        // Ensure the PDFs directory exists
        Path pdfFolderPath = Paths.get("./PDFs");
        if (!Files.exists(pdfFolderPath)) {
            Files.createDirectories(pdfFolderPath);
        }

        // Save the PDF file
        Path pdfFilePath = pdfFolderPath.resolve("selenium.pdf");
        Pdf pdf = ((PrintsPage) driver).print(new PrintOptions());
        Files.write(pdfFilePath, OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
    }


    @Test
    public static void Screenshots() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://way2automation.com");
                        log.info("Navigated to: " + driver.getCurrentUrl());
                        Thread.sleep(2000);

                        // Get the project directory dynamically
                        String projectDir = System.getProperty("user.dir");
                        // Define the Screenshots folder path inside the project directory
                        File screenshotFolder = new File(Paths.get(projectDir, "Screenshots").toString());
                        if (!screenshotFolder.exists()) {
                            screenshotFolder.mkdirs(); // Create the folder if it doesn’t exist
                        }

                        // Wait for the element to be visible before capturing it
                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Mzk4OjY4NA==-1\"]")));

                        // Visible page screenshot
                        File visiblePage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(visiblePage, new File(screenshotFolder, "visiblePage.png"));

                        // Element screenshot
                        File elementScreenshot = element.getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(elementScreenshot, new File(screenshotFolder, "element.png"));

                        // Capture full-page screenshot using AShot
                        Screenshot fullPageScreenshot = new AShot()
                                .shootingStrategy(ShootingStrategies.viewportPasting(100)) // Scroll and stitch
                                .takeScreenshot(driver);
                        // Save the screenshot
                        BufferedImage image = fullPageScreenshot.getImage();
                        ImageIO.write(image, "PNG", new File(screenshotFolder, "fullPage.png"));

                    } catch (NoAlertPresentException e) {
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                });
    }
}
