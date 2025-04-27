package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.qameta.allure.Allure;
import org.testng.*;
import org.testng.reporters.EmailableReporter2;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static Utilities.EmailConfigReader.*;

public class TestListeners extends Setup implements ITestListener, ISuiteListener, IReporter {
    private ISuite suite;
    public static String REPORT_DIR;
    public static String reportFolderName;
    public static ExtentSparkReporter spark;
    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static String methodName;

    @Override
    public void onStart(ISuite suite) {
        this.suite = suite;
        // Initialize report directory once at suite start
        REPORT_DIR = "Reports for Email";
        File reportFolder = new File(REPORT_DIR);
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        } else {
            TestConfig.deleteDirectory(reportFolder);
            reportFolder.mkdirs();
        }

        //For Allure Report
        String AllureReport = "./allure-results";
        File AllureReport_reportFolder = new File(AllureReport);
        if (!AllureReport_reportFolder.exists()) {
            AllureReport_reportFolder.mkdirs();
        } else {
            TestConfig.deleteDirectory(AllureReport_reportFolder);
            AllureReport_reportFolder.mkdirs();
        }


        // Initialize log directory
        File logDir = new File("./logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        } else {
            TestConfig.deleteDirectory(new File("./logs"));
            logDir.mkdirs();
        }

        String timestamp = new SimpleDateFormat("dd_MM_yy hh a").format(new Date());
        reportFolderName = REPORT_DIR + "/Automation Test Report of " + timestamp;


        // Setup Extent Spark Reporter - do this once
        spark = new ExtentSparkReporter("./" + reportFolderName + "/Test Report - Extent.html");
        spark.config().setEncoding("utf-8");
        spark.config().setDocumentTitle("Automation Extent Report");
        spark.config().setReportName("Automation Extent Report");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add environment info
        String projectName = new File(System.getProperty("user.dir")).getName();
        extent.setSystemInfo("Project", projectName.toUpperCase());
        extent.setSystemInfo("Tester", "Fahim");
        extent.setSystemInfo("Role", "SQA Engineer");
        extent.setSystemInfo("Type", "Automation Testing");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod().getMethodName().toUpperCase());
        methodName = result.getMethod().getMethodName();
        extentTest.set(extent.createTest(methodName));

        // Add test parameters if any
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            extentTest.get().info("Test Parameters: " + String.join(", ", parameters.toString()));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName().toUpperCase());
        methodName = result.getMethod().getMethodName().toUpperCase();
        Markup m = MarkupHelper.createLabel(methodName + " - PASSED", ExtentColor.GREEN);
        extentTest.get().pass(m);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        methodName = result.getMethod().getMethodName().toUpperCase();
        try {
            // 1. Capture screenshot and get full path
            String screenshotRelativePath = TakeScreenshotUsingAshot.sshot(methodName, reportFolderName);
            String screenshotFullPath = reportFolderName + File.separator + screenshotRelativePath;

            // 2. Attach to Allure report (using proper file handling)
            try (InputStream img = Files.newInputStream(Paths.get(screenshotFullPath))) {
                Allure.addAttachment("Failure Screenshot - " + methodName, "image/png", img, "png");
            }

            // 3. Attach to Extent report
            extentTest.get().fail("Test Failed Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotRelativePath).build());

        } catch (Exception e) {
            extentTest.get().warning("Failed to capture screenshot: " + e.getMessage());
            Allure.addAttachment("Screenshot Error", "text/plain",
                    "Failed to capture screenshot: " + e.getMessage());
        }


        Markup m = MarkupHelper.createLabel(methodName + " - FAILED", ExtentColor.RED);
        extentTest.get().fail(m);
        extentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName().toUpperCase());
        methodName = result.getMethod().getMethodName().toUpperCase();
        Markup m = MarkupHelper.createLabel(methodName + " - SKIPPED", ExtentColor.ORANGE);
        extentTest.get().skip(m);
        extentTest.get().skip("Skip Reason: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional implementation
        methodName = result.getMethod().getMethodName().toUpperCase();
        extentTest.get().warning("Test failed but within success percentage: " + methodName);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        methodName = result.getMethod().getMethodName().toUpperCase();
        extentTest.get().fail("Test failed due to timeout: " + methodName);
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional implementation
        System.out.println("Test Context started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Optional implementation
        System.out.println("Test Context finished: " + context.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("All tests finished. Finalizing reports...");
        extent.flush();
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // Ensure the report directory exists
        File reportDir = new File(reportFolderName);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Generate the TestNG emailable report
        System.out.println("Generating TestNG emailable report...");
        EmailableReporter2 report = new EmailableReporter2();

        // Generate the TestNG report
//        report.generateReport(xmlSuites, suites, reportFolderName);

        System.out.println("Report generated successfully!");

        // Create zip file
        TestConfig.zipfilepath = reportFolderName + ".zip";
        TestConfig.folderpath = reportFolderName;
        TestConfig.zipScreenshots(TestConfig.folderpath, TestConfig.zipfilepath);

        // Verify zip file exists
        File zipFile = new File(TestConfig.zipfilepath);
        if (zipFile.exists()) {
            System.out.println("Zip file created successfully: " + TestConfig.zipfilepath);
        } else {
            System.out.println("Zip file not found: " + TestConfig.zipfilepath);
        }

        // Send test results email with attachments
        MonitoringMail.sendMail(
                getMailServer(),
                getFrom(),
                getPassword(),
                getTo(),
                TestConfig.subject,
                TestConfig.messageBody,
                new String[]{TestConfig.zipfilepath}  // Attach the zip file
        );
    }
}