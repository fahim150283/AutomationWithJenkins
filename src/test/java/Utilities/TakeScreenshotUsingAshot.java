package Utilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TakeScreenshotUsingAshot extends Setup {

    public static String sshot(String methodname, String SavePath) throws IOException, InterruptedException {
        Thread.sleep(200);
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.simple())
                .takeScreenshot(driver);

        // Create screenshots directory if it doesn't exist
        File screenshotsDir = new File(SavePath + "/screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
            System.out.println("Screenshot directory created: " + screenshotsDir.getAbsolutePath());
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
        String timestamp = LocalDateTime.now().format(dtf);
        String fileName = methodname + "-" + timestamp + ".png";
        String filePath = screenshotsDir.getAbsolutePath() + File.separator + fileName;

        ImageIO.write(screenshot.getImage(), "png", new File(filePath));
        System.out.println("Screenshot saved: " + filePath);

        // Return relative path from report directory
        return "screenshots/" + fileName;
    }
}