package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm");
        String currentDate = LocalDateTime.now().format(dtf);
        String zipFilePath = "Reports for Email/Reports" + currentDate + ".zip",
                folderPath = "screenshot";
        zipFilePath(folderPath, zipFilePath);
    }

    private static void zipFilePath(String folderPath, String zipFilePath) {
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("No screenshots to zip.");
                return;
            }

            String today = new SimpleDateFormat("yyyy_MM_dd").format(new Date());

            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            boolean filesAdded = false;

            for (File file : files) {
                if (file.isFile() && file.getName().contains(today)) { // Filter files with today's date
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }

                    fis.close();
                    filesAdded = true;
                }
            }

            zipOut.close();
            fos.close();

            if (filesAdded) {
                System.out.println("Screenshots zipped successfully.");
            } else {
                System.out.println("No screenshots found for today's date: " + today);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}