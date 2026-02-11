package pages;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserWindowHandlingPage {

    private WebDriver driver;

    // Dynamic locators
    private By sectionByText(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

//    private By buttonByText(String text) {
//        return By.xpath("//button[text()='" + text + "']");
//    }
    
    private By buttonByText(String buttonName) {
        return By.xpath(
            "//div[contains(@class,'header-text') and contains(normalize-space(.),'" 
            + buttonName + "')]"
        );
    }


    private By pageHeader = By.xpath("//div[@class='main-header']");
    private By samplePageText = By.xpath("//h1");

    public BrowserWindowHandlingPage(WebDriver driver) {
        this.driver = driver;
    }

    /* ---------------- Actions ---------------- */
    
    public List<String> getAllLeftPanelOptions() {
        List<WebElement> options =
            driver.findElements(By.cssSelector(".element-list .menu-list li span"));

        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    public void clickOnSection(String sectionName) {
        driver.findElement(sectionByText(sectionName)).click();
    }

    public String getHeaderText() {
        return driver.findElement(pageHeader).getText();
    }

    public void clickOnButton(String buttonName) {
        driver.findElement(buttonByText(buttonName)).click();
    }

    public String getSamplePageText() {
        return driver.findElement(samplePageText).getText();
    }

    /* ---------------- Window Utilities ---------------- */

    public String getParentWindow() {
        return driver.getWindowHandle();
    }

    public void switchToNewWindow(String parentWindow) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void switchToWindow(String windowId) {
        driver.switchTo().window(windowId);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
}
