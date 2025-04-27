package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestConfig {

    public static String subject;
    public static String messageBody;
    public static String[] attachmentPaths;
    public static String zipfilepath, folderpath;

    static {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm a - dd/MM/yyyy");
            String formattedDate = localDateTime.format(myFormatObj);

            subject = "Automation Test Report On " + formattedDate;
            messageBody =
                    "<html>" +
                            "<body style='font-family: Arial, sans-serif; font-size: 14px; color: #333;'>" +
                            "   <p>Greetings!</p>" +
                            "   <p>This email contains the automation test report. Please find the attached files for details.</p>" +
                            "   <p>Thank you.</p>" +
                            "   <p style='font-size: 12px;'>Best regards,<br>Automation Team</p>" +
                            "   <p>" +
                            "   <p color=#FF0000><strong>NB:</strong> This is an auto-generated email. Please do not reply.</p>" +
                            "</body>" +
                            "</html>";


            String reportsDir = "Reports for Email";

            // Ensure "Reports for Email" directory exists
            File reportsFolder = new File(reportsDir);
            if (!reportsFolder.exists()) {
                reportsFolder.mkdirs(); // Creates directory if it doesn't exist
            }

            zipfilepath = TestListeners.reportFolderName + ".zip";
            folderpath = TestListeners.reportFolderName;
            zipScreenshots(folderpath, zipfilepath);
            attachmentPaths = new String[]{zipfilepath};


            Thread.sleep(2000);
        } catch (NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void zipScreenshots(String folderPath, String zipFilePath) {
        try {
            System.out.println("Zipping all contents of the folder: " + folderPath);

            File folder = new File(folderPath);
            if (!folder.exists() || folder.listFiles() == null || Objects.requireNonNull(folder.listFiles()).length == 0) {
                System.out.println("No files or folders to zip.");
                return;
            }

            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            for (File file : folder.listFiles()) {
                zipFile(file, file.getName(), zipOut);
            }

            zipOut.close();
            fos.close();

            System.out.println("All contents zipped successfully: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        }
    }

    private static void zipFile(File file, String zipEntryName, ZipOutputStream zipOut) throws IOException, IOException {
        if (file.isDirectory()) {
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                zipFile(childFile, zipEntryName + "/" + childFile.getName(), zipOut);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(file)) {
                ZipEntry zipEntry = new ZipEntry(zipEntryName);
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int GenerateRandomNumber() {
        Random rand = new Random();
        int ranNum = rand.nextInt(1000000);
        return ranNum;
    }

    /**
     * Helper method to recursively delete a directory and its contents.
     */
    public static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file); // Recursively delete files and subdirectories
                }
            }
        }
        directory.delete(); // Delete the directory itself
    }
}