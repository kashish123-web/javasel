package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("user launches the application")
    public void userLaunchesTheApplication() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.launchApplication();
    }
  
    @When("user navigates to Book Store Application")
    public void userNavigatesToBookStoreApplication() throws InterruptedException {
        loginPage.openBookStoreApplication();
        System.out.println("Clicked on Login Button");
    }

    @When("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Then("user should be redirected to books page")
    public void userShouldBeRedirectedToBooksPage() {
        Assert.assertTrue(
                loginPage.isOnBooksPage(),
                "User is NOT redirected to Books page"
        );
    }

}
