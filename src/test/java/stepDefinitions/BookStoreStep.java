package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookStorePage;
import utils.DriverFactory;

public class BookStoreStep {

    WebDriver driver = DriverFactory.getDriver();
    BookStorePage bookStorePage = new BookStorePage(driver);

    @When("user searches book by {string} with value {string}")
    public void userSearchesBook(String searchType, String searchValue) {
        bookStorePage.searchBook(searchValue);
        System.out.println("Searching by " + searchType + " : " + searchValue);
    }

    @Then("user should see correct search result for {string} with value {string}")
    public void userShouldSeeCorrectSearchResult(String searchType, String searchValue) {

        // 🔴 INVALID SEARCH CASE
        if (searchValue.equalsIgnoreCase("InvalidBookName")) {

            Assert.assertTrue(
                bookStorePage.isNoRowsFoundVisible(),
                "'No rows found' message is not visible"
            );

            Assert.assertEquals(
                bookStorePage.getNoRowsFoundText(),
                "No rows found",
                "Incorrect message shown for invalid search"
            );
            return;
        }

        // 🟢 VALID SEARCH CASE
        boolean result =
            bookStorePage.verifyColumnContainsValue(searchType, searchValue);

        Assert.assertTrue(
            result,
            "Search results mismatch for " + searchType +
            " | Expected value: " + searchValue
        );
    }
}
