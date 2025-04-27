package TestCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class TestExtentReport {
    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeTest
    public void setReport() {
        // Create the folder if it doesn't exist
        File reportDir = new File("./ExtentReports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Setup Extent Spark Reporter
        spark = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
        spark.config().setEncoding("utf-8");
        spark.config().setDocumentTitle("Automation Extent Report");
        spark.config().setReportName("Automation Extent Report");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add environment info
        extent.setSystemInfo("Project", "Test Automation Learning");
        extent.setSystemInfo("Tester", "Fahim");
        extent.setSystemInfo("Role", "Student - Backbencher");
        extent.setSystemInfo("Mode", "Fun + Focus");
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }

    // ------------------ Test Methods ------------------ //

    @Test
    public void testPass() {
        test = extent.createTest("Pass Test", "This test will pass.");
        System.out.println("Executing Pass Test");
        Assert.assertTrue(true);
    }

    @Test
    public void testFail() {
        test = extent.createTest("Fail Test", "This test will fail.");
        System.out.println("Executing Fail Test");
        Assert.fail("Intentional Failure for demo.");
    }

    @Test
    public void testSkip() {
        test = extent.createTest("Skip Test", "This test will be skipped.");
        System.out.println("Executing Skip Test");
        throw new SkipException("Intentional Skip for demo.");
    }

    // ------------------ Result Handler ------------------ //

    @AfterMethod
    public void tearDown(ITestResult result) {
        String methodName = result.getMethod().getMethodName().toUpperCase();
//        String stackTrace = result.getThrowable() != null
//                ? Arrays.toString(result.getThrowable().getStackTrace())
//                : "";

        if (result.getStatus() == ITestResult.FAILURE) {
            Markup m = MarkupHelper.createLabel(methodName + " - FAILED", ExtentColor.RED);
            test.fail(m);
            test.fail("Exception: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            Markup m = MarkupHelper.createLabel(methodName + " - SKIPPED", ExtentColor.ORANGE);
            test.skip(m);
            test.skip("Skip Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            Markup m = MarkupHelper.createLabel(methodName + " - PASSED", ExtentColor.GREEN);
            test.pass(m);
        }
    }
}
