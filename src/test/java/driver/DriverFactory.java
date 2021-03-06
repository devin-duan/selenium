package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    // Get a new WebDriver Instance.
    // There are various implementations for this depending on browser. The required browser can be set as an environment variable.
    // Refer http://getgauge.io/documentation/user/current/managing_environments/README.html
    public static WebDriver getDriver() {

        String browser = System.getenv("BROWSER");
        browser = (browser == null) ? "CHROME": browser;

        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();

	            ChromeOptions options = new ChromeOptions();
	            // if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
	                options.addArguments("--headless");
	                options.addArguments("--disable-gpu");
                    // To fix browser crash issue
                    options.addArguments("--no-sandbox");
                    options.addArguments("--start-maximized");

                    // To fix browser hanging-up issue
                    options.addArguments("--dns-prefetch-disable");
                    options.addArguments("--always-authorize-plugins");
	            // } else {
                //     options.addArguments("--no-sandbox");
                // }

	            return new ChromeDriver(options);
        }
    }
}
