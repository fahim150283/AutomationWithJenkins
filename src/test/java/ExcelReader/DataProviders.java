package ExcelReader;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviders {


    @DataProvider(name = "dp1")
    public Object[][] getData() {
        Object[][] data = new Object[2][2];
        data[0][0] = "John";
        data[0][1] = "Doe";

        data[1][0] = "Jane";
        data[1][1] = "Hoe";

        return data;
    }

    @DataProvider(name = "dp2")
    public Object[][] getData2() {
        Object[][] data = new Object[2][3];
        data[0][0] = "John";
        data[0][1] = "Doe";
        data[0][2] = "john.doe@example.com";

        data[1][0] = "Jane";
        data[1][1] = "Hoe";
        data[1][2] = "jane.hoe@example.com";

        return data;
    }


    @DataProvider(name = "dp3")
    public Object[][] DataForMethodTest(Method method) {

        Object[][] data = null;
        if (method.getName().equals("testLogin")) {
            data = new Object[2][2];
            data[0][0] = "John";
            data[0][1] = "Doe";
            data[1][0] = "Jane";
            data[1][1] = "Hoe";
        } else if (method.getName().equals("testUserReg")) {
            data = new Object[2][3];
            data[0][0] = "John";
            data[0][1] = "Doe";
            data[0][2] = "john.doe@example.com";

            data[1][0] = "Jane";
            data[1][1] = "Hoe";
            data[1][2] = "jane.hoe@example.com";
        }


        return data;
    }
}

