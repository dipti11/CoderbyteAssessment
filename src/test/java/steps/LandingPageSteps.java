package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageobjects.LandingPage;
import utils.CommonUtils;
import utils.DriverUtils;

import java.io.IOException;

public class LandingPageSteps {

    private WebDriver driver;
    private LandingPage landingPage;
    private final CommonUtils commonUtils;
    private final DriverUtils driverUtils;
    private final SoftAssert softAssert;

    public LandingPageSteps() {
        this.driverUtils = new DriverUtils();
        this.commonUtils = new CommonUtils();
        this.softAssert = new SoftAssert();
    }

    @Before
    public void setUp() {
        try {
            driverUtils.setUp("chrome");
            this.driver = driverUtils.getDriver();
            this.landingPage = new LandingPage(driver);
            String url = commonUtils.readProperties("appUrl");
            driver.get(url);
            driverUtils.maximizeWindow();
        } catch (Exception e) {
            Assert.fail("Driver not initialized");
        }
    }

    @Given("I am on the landing page")
    public void i_am_on_the_landing_page() throws IOException {
        String expected = commonUtils.readProperties("appUrl");
        String actual = driver.getCurrentUrl();
        boolean condition = landingPage.isLandingPageDisplayed();
        String reasonForFailure = "Url does not match";
        Assert.assertEquals(reasonForFailure,expected,actual);
        reasonForFailure = "Landing page is not displayed";
        Assert.assertTrue(reasonForFailure, condition);
    }

    @When("I scroll down to the carousel")
    public void i_scroll_down_to_the_carousel() throws InterruptedException {
        landingPage.scrollToCarousel();
    }

    @Then("I should see the carousels")
    public void i_should_see_the_carousel() {
        boolean condition = landingPage.isCarouselDisplayed();
        String reasonForFailure = "Carousel is not displayed";
        Assert.assertTrue(reasonForFailure, condition);
    }

    @Then("there should be {int} carousels")
    public void there_should_be_carousels(int expected) {
        int actual = landingPage.getCarouselCount();
        String reasonForFailure = "number of carousels are not correct";
        Assert.assertEquals(reasonForFailure, expected, actual);
    }

    @Then("I should see {int} items in the carousel {int}")
    public void i_should_see_items_in_each_carousel(int expected, int carouselNumber) {
        int actual = landingPage.getCarouselItemsCount(carouselNumber);
        String reasonForFailure = "Carousel number " + carouselNumber + " does not contain " + expected + " items";
        Assert.assertEquals(reasonForFailure, expected, actual);
    }

    @Then("I should see {int} items in each carousel")
    public void i_should_see_items_in_each_carousel(int expected) {
        int numberOfCarousels = landingPage.getCarouselCount();
        int actual;
        String reasonForFailure;
        for(int i=1;i<=numberOfCarousels;i++){
            actual = landingPage.getCarouselItemsCount(i);
            reasonForFailure = "Carousel number " + i + " does not contain " + expected + " items";
            softAssert.assertEquals(actual,expected,reasonForFailure);
        }
        softAssert.assertAll();
    }

    @After
    public void tearDown() {
        driverUtils.tearDown();
    }
}
