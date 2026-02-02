package stepDefinitions;

import io.cucumber.java.After;
import utils.DriverFactory;

public class Hooks {

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        //System.out.println("Browser Driver Closed Successfully");
    }
}
