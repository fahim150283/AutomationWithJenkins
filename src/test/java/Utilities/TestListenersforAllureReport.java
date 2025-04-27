package Utilities;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class TestListenersforAllureReport implements ITestListener {

    public InputStream is;

    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
    }

    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onTestFailure(ITestResult result) {
        try {
            is = new FileInputStream("C:\\Users\\TechTeam-08\\Pictures\\Camera Roll\\8t6ojqvtqm4a1.png");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Allure.addAttachment("Screenshot", is);


    }

    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

        TestConfig.deleteDirectory(new File("./allure-results"));
    }

    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub

    }

}
