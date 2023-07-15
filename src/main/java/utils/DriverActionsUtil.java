package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverActionsUtil {

    private final WebDriver driver;

    public DriverActionsUtil(WebDriver driver) {
        this.driver = driver;
    }

    // Scroll to the element
    public void scrollTillElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
