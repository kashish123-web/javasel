package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BookStorePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 Locators
    private By searchBox = By.id("searchBox");

    private By tableRows =
        By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr-group']");

    // EXACT text shown in UI
    private By noRowsFoundText =
        By.xpath("//*[normalize-space()='No rows found']");

    public BookStorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Search
    public void searchBook(String value) {
        WebElement search =
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(value);
    }

    // 🔴 No rows found visible
    public boolean isNoRowsFoundVisible() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(noRowsFoundText)
            ).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // 🔴 Actual DOM text
    public String getNoRowsFoundText() {
        return driver.findElement(noRowsFoundText).getText().trim();
    }

    // 🟢 Validate table rows
    public boolean verifyColumnContainsValue(String columnType, String expectedValue) {

        int columnIndex;

        switch (columnType.toLowerCase()) {
            case "title":
                columnIndex = 2;
                break;
            case "author":
                columnIndex = 3;
                break;
            case "publisher":
                columnIndex = 4;
                break;
            default:
                throw new IllegalArgumentException(
                    "Invalid column type: " + columnType
                );
        }

        List<WebElement> rows =
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRows));

        boolean validRowFound = false;

        for (WebElement row : rows) {

            String cellText = row
                .findElement(By.xpath(".//div[@class='rt-td'][" + columnIndex + "]"))
                .getText()
                .trim();

            // 🔥 React empty row skip
            if (cellText.isEmpty()) {
                continue;
            }

            validRowFound = true;

            System.out.println("Expected (contains): " + expectedValue);
            System.out.println("Actual              : " + cellText);

            if (!cellText.toLowerCase().contains(expectedValue.toLowerCase())) {
                return false;
            }
        }

        return validRowFound;
    }
}
