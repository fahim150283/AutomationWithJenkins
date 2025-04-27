package TestCases;

import Utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class TestHandlingCalendars extends Setup {


    static int targetDay = 0,
            targetMonth = 0,
            targetYear = 0;
    static String targetDayOfWeek = "Tuesday",
            getTargetmonth = "September";

    static int currentDay = 0,
            currentMonth = 0,
            currentYear = 0;

    static int jumpMonthsBy = 0;

    static boolean increment = true;

    @Test
    public static void TestHandlingCalendars() throws InterruptedException {


        String dateToSet = "09/01/2025";

        //get current date
        getCurrentDateMonthAndYear();
        System.out.println("current day is : " + currentDay + "-" + currentMonth + "-" + currentYear);

        //get target date
        GetTargetDateMonthAndYear(dateToSet);
        System.out.println("target day is : " + targetDay + "-" + targetMonth + "-" + targetYear);


        //Get Jump month
        CalculateHowManyMonthsToJump();
        System.out.println("jumpMonthsBy is : " + jumpMonthsBy);
        System.out.println("increment is : " + increment);


        driver.get("https://demo.mobiscroll.com/jquery/calendar/mobile-desktop-usage#touchUi=false");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"show-picker\"]")));
        driver.findElement(By.xpath("//*[@id=\"show-picker\"]")).click();
        Thread.sleep(2000);

        for (int i = 0; i < jumpMonthsBy; i++) {
            if (increment) {
                if (jumpMonthsBy > 0) {
                    driver.findElement(By.xpath("//*[@id=\"demo-static-date-usage-context-top\"]/div[3]/div[2]/div[2]/div/div/div/div/div/div/div/div/button[1]")).click();
                } else {
                    driver.findElement(By.xpath("//*[@id=\"demo-static-date-usage-context-top\"]/div[3]/div[2]/div[2]/div/div/div/div/div/div/div/div/button[2]")).click();
                }
            }
        }

        // Select the target date
        xpath = "//div[@class=\"mbsc-calendar-table mbsc-flex-col mbsc-flex-1-1 mbsc-calendar-table-active\"]/div/div/div/div[@aria-label='" + targetDayOfWeek + ", " + getTargetmonth + " " + targetDay + ", " + targetYear + "']";
        System.out.println(targetDayOfWeek + ", " + getTargetmonth + " " + targetDay + ", " + targetYear);
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        dateElement.click();

        System.out.println("Visible date is : " + driver.findElement(By.xpath("//*[@id=\"button-picker\"]")).getAttribute("value"));
    }


    public static void getCurrentDateMonthAndYear() {
        Calendar cal = Calendar.getInstance();

        currentDay = cal.get(Calendar.DAY_OF_MONTH);
        currentMonth = cal.get(Calendar.MONTH) + 1;
        currentYear = cal.get(Calendar.YEAR);
    }


    public static void GetTargetDateMonthAndYear(String dateString) {


        int firstIndex = dateString.indexOf("/");
        int lastIndex = dateString.lastIndexOf("/");

        // Extract day, month, and year
        targetDay = Integer.parseInt(dateString.substring(0, firstIndex));
        int monthNumber = Integer.parseInt(dateString.substring(firstIndex + 1, lastIndex));
        targetYear = Integer.parseInt(dateString.substring(lastIndex + 1));

        // Convert month number to full month name (e.g., 9 -> "September")
        LocalDate targetDate = LocalDate.of(targetYear, monthNumber, targetDay);


        String month = dateString.substring(firstIndex + 1, lastIndex);
        getTargetmonth = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        targetMonth = Integer.parseInt(month);
        // Get the day of the week (e.g., "Thursday")
        targetDayOfWeek = targetDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

    }


    public static void CalculateHowManyMonthsToJump() {


        if ((targetMonth - currentMonth) > 0) {
            jumpMonthsBy = (targetMonth - currentMonth);
        } else if ((currentMonth - targetMonth) > 0) {
            jumpMonthsBy = (currentMonth - targetMonth);

        } else {
            jumpMonthsBy = (currentMonth - targetMonth);
            increment = false;
        }


    }
}
