package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {

			String browser = ConfigReader.get("browser");

			if (browser == null) {
				throw new RuntimeException("Browser is not defined in config.properties");
			}

			if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}

			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
