package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverActionsUtil;

import java.time.Duration;
import java.util.List;

public class LandingPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final DriverActionsUtil driverActionsUtil;

    @FindBy(xpath = "//div[@class='cms-carousel-b__slider-wrapper']")
    private List<WebElement> carousels;

    @FindBy(className = "header__big-logo")
    private WebElement logo;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        this.driverActionsUtil = new DriverActionsUtil(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isLandingPageDisplayed() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
        return logo.isDisplayed();
    }

    public void scrollToCarousel() throws InterruptedException {
        for(WebElement carousel: carousels) {
            driverActionsUtil.scrollTillElement(carousel);
            Thread.sleep(1000);
        }
    }

    public boolean isCarouselDisplayed() {
        return !carousels.isEmpty();
    }

    public int getCarouselItemsCount(int carouselIndex) {
        return driver.findElements(By.xpath("(//div[@class='cms-carousel-b__slider-wrapper'])["+carouselIndex+"]//li[@id='NaN']")).size();
    }

    public int getCarouselCount() {
        return carousels.size();
    }
}
