package utils;

import ch.qos.logback.classic.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;

public class DriverUtils {

    private WebDriver driver;

    Logger logger = (Logger) LoggerFactory.getLogger(DriverUtils.class);

    // Method to initialize the driver
    public void setUp(String browser) {
        switch (browser) {
            case "chrome":
                setUpChrome();
                break;
            case "firefox":
                setUpFirefox();
                break;
            case "edge":
                setUpEdge();
                break;
            default:
                logger.error("Invalid browser name");
        }
    }

    // Method to set up Chrome driver
    private void setUpChrome() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        // Set ChromeOptions whichever is required below is just an example
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--headless");
        // Initialize the WebDriver
        driver = new ChromeDriver(options);
    }

    // Method to set up Edge driver
    private void setUpEdge() {
        // Set EdgeDriver path
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
        // Initialize the WebDriver
        driver = new EdgeDriver();
    }

    // Method to set up Firefox driver
    private void setUpFirefox() {
        // Set FirefoxDriver path
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
        // Initialize the WebDriver
        driver = new FirefoxDriver();
    }

    // Method to maximize the browser window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Method to quit the driver
    public void tearDown() {
        driver.quit();
    }

    // Getter method to return the driver
    public WebDriver getDriver() {
        return driver;
    }
}
