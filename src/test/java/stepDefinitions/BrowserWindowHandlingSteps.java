package stepDefinitions;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.*;
import pages.BrowserWindowHandlingPage;
import utils.DriverFactory;

public class BrowserWindowHandlingSteps {

    WebDriver driver = DriverFactory.getDriver();
    BrowserWindowHandlingPage bwPage = new BrowserWindowHandlingPage(driver);

    String parentWindow;
    
    @Then("user should see options:")
    public void user_should_see_options(io.cucumber.datatable.DataTable dataTable) {

        List<String> expectedOptions = dataTable.asList();
        List<String> actualOptions = bwPage.getAllLeftPanelOptions();

        assertTrue(
            actualOptions.containsAll(expectedOptions),
            "Expected options are not present in UI"
        );
    }


    @Given("user clicks on {string} section")
    public void user_clicks_on_section(String sectionName) {
        bwPage.clickOnSection(sectionName);
    }

    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String pageName) {
        bwPage.clickOnSection(pageName);
    }

    @Then("user should see {string} text displayed at center of the screen")
    public void user_should_see_text_displayed(String expectedText) {
        assertEquals(expectedText, bwPage.getHeaderText());
    }

    @When("user clicks on {string} button")
    public void user_clicks_on_button(String buttonName) {
        parentWindow = bwPage.getParentWindow();
        bwPage.clickOnButton(buttonName);
    }

    @Then("a new tab should be opened")
    public void a_new_tab_should_be_opened() {
    	assertTrue(driver.getWindowHandles().size() > 1);
    }

    @Then("a new window should be opened")
    public void a_new_window_should_be_opened() {
    	assertTrue(driver.getWindowHandles().size() > 1);
    }

    @Then("user switches to newly opened tab")
    public void user_switches_to_new_tab() {
        bwPage.switchToNewWindow(parentWindow);
    }

    @Then("user switches to newly opened window")
    public void user_switches_to_new_window() {
        bwPage.switchToNewWindow(parentWindow);
    }

    @Then("user should see the text {string}")
    public void user_should_see_the_text(String expectedText) {
        assertEquals(expectedText, bwPage.getSamplePageText());
    }

    @Then("a new window should be opened with URL {string}")
    public void a_new_window_should_be_opened_with_url(String expectedUrl) {
        bwPage.switchToNewWindow(parentWindow);
        assertEquals(expectedUrl, bwPage.getCurrentUrl());
    }

    @Then("user should see the message text:")
    public void user_should_see_the_message_text(String expectedMessage) {
    	String actualText = driver.getPageSource();
    	assertTrue(actualText.contains(expectedMessage.trim()));
        assertTrue(actualText.contains(expectedMessage.trim()));
    }

    @Then("user closes the current window")
    public void user_closes_the_current_window() {
        bwPage.closeCurrentWindow();
    }

    @Then("user switches back to parent window")
    public void user_switches_back_to_parent_window() {
        bwPage.switchToWindow(parentWindow);
    }
}
