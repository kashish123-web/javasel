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
    public void user_launches_the_application() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.launchApplication();
    }

    @When("user navigates to Book Store Application")
    public void user_navigates_to_book_store_application() {
        loginPage.openBookStoreApplication();
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Then("user should be redirected to books page")
    public void user_should_be_redirected_to_books_page() {
        Assert.assertTrue(
                loginPage.isOnBooksPage(),
                "User is NOT redirected to Books page"
        );
    }
}
