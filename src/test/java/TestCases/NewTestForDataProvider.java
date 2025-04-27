package TestCases;

import ExcelReader.DataProviders;
import org.testng.annotations.Test;

public class NewTestForDataProvider {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp1")
    public void testDp1(String firstName, String lastName) {
        System.out.println("Test 1");
        System.out.println(firstName + " " + lastName);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp2")
    public void testDp2(String firstName, String lastName, String email) {
        System.out.println("Test 2");
        System.out.println(firstName + " " + lastName + " " + email);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp3")
    public void testLogin(String firstName, String lastName) {
        System.out.println("Test 3");
        System.out.println(firstName + " " + lastName);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp3")
    public void testUserReg(String firstName, String lastName, String email) {
        System.out.println("Test 4");
        System.out.println(firstName + " " + lastName + " " + email);
    }
}
