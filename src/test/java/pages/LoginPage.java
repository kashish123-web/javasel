package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 LOCATORS (ONLY HERE)
    private By bookStoreCard = By.xpath("//h5[text()='Book Store Application']");
    private By loginButton = By.id("login");
    private By usernameField = By.id("userName");
    private By passwordField = By.id("password");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 LOGIC METHODS ONLY

    public void launchApplication() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void openBookStoreApplication() {
        WebElement bookStore = wait.until(
                ExpectedConditions.visibilityOfElementLocated(bookStoreCard)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", bookStore);

        wait.until(ExpectedConditions.elementToBeClickable(bookStore)).click();
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);

        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isOnBooksPage() {
        return driver.getCurrentUrl().contains("/books");
    }
}
