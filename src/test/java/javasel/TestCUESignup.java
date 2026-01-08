package javasel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCUESignup {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			String url = ConfigReader.get("url");
			String username = ConfigReader.get("username");
			String password = ConfigReader.get("password");
			driver.get(url);
			driver.manage().window().maximize();
			System.out.println("Navigated to URL: " + url);
			Thread.sleep(3000);
			WebElement usernameField = driver.findElement(By.xpath("//input[@type='text' and @aria-invalid='false']"));
			usernameField.sendKeys(username);
			WebElement passwordField = driver
					.findElement(By.xpath("//input[@type='password' and @aria-invalid='false']"));
			passwordField.sendKeys(password);
			WebElement loginButton = driver.findElement(By.xpath("//button[@type='button' and text()='Login']"));
			loginButton.click();
			Thread.sleep(3000);
			WebElement cueLogo = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='dxp-header-logo']")));
			if (cueLogo.isDisplayed()) {
				System.out.println("Cue logo is displayed!");
			} else {
				System.out.println("Cue logo is not displayed!");
			}
			
			WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg[data-testid='PersonIcon']")));
			profileIcon.click();
			System.out.println("Clicked on Profile Logo");

	
			WebElement mainMenuText = driver.findElement(By.xpath("//p[text()= 'Main Menu']"));
			mainMenuText.click();
			System.out.println("Clicked on mainMenuText");
			
//			WebElement signUpTab = driver.findElement(By.xpath("//span[text()='Sign Up']"));
//			signUpTab.click();
//			System.out.println("Clicked on Signup Tab");
//			Thread.sleep(3000);

			WebElement platformArcadeDropDown = driver.findElement(By.xpath("//span[text()='Platform Arcade']"));
			platformArcadeDropDown.click();
			System.out.println("Clicked on platformArcadeDropDown");
			
			WebElement showMobileLinkButton = driver.findElement(By.xpath("//button[@aria-label='dxp-menu-settings-configuration-manager-add-new-preset-tooltip']"));
			showMobileLinkButton.click();
			
	////Handle popup
			
			

			WebElement copyButton = driver.findElement(By.xpath("//button[contains(@class, 'MuiButton-root')]//svg[@data-testid='ContentCopyIcon']"));
			copyButton.click();
			
			

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
			System.out.println("Browser closed.");
		}
	}
}

// ConfigReader class (you need to add this to your project)
class ConfigReader {
	private static Properties props = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}
