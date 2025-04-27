package TestCases;

import Utilities.Setup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestElementScreenshot extends Setup {

    public static void captureScreenshot() throws IOException {

        Date d = new Date();
        String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(".//screenshot//" + fileName));

    }

    public static void captureEleScreenshott(WebElement ele) throws IOException {

        Date d = new Date();
        String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";


        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        BufferedImage fullImg = ImageIO.read(screenshot);

        Point point = ele.getLocation();

        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "jpg", screenshot);

        File screenshotLocation = new File(".\\screenshot\\" + fileName);
        FileUtils.copyFile(screenshot, screenshotLocation);

    }

    @Test
    public static void TestElementScreenshott() throws IOException {


        driver.get("http://www.way2automation.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement ele = driver.findElement(By.xpath("/html/body/div[4]/div/header/div[2]/div/div[1]"));
        captureEleScreenshott(ele);
        captureScreenshot();
    }

}
