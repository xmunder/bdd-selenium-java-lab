package steps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchSteps {
	
    private static WebDriver driver;
		private static String WIDTH = "1920";
		private static String HEIGHT = "1080";
		private static String LANG = "es-ES";
		private static String ACCEPT_LANG = "es-ES,es";
		private static String REMOTE_URL = "http://selenium:4444/wd/hub";
		private static String BROWSER_URL = "https://duckduckgo.com/";

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        try {
            String remoteUrl = System.getenv().getOrDefault("SELENIUM_REMOTE_URL", REMOTE_URL);
            ChromeOptions options = new ChromeOptions();
						options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
						options.addArguments("--lang=" + LANG);
						options.addArguments("--accept-lang=" + ACCEPT_LANG);	
						options.addArguments("--window-size=" + WIDTH + "," + HEIGHT);
            driver = new RemoteWebDriver(new URL(remoteUrl), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid SELENIUM_REMOTE_URL", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ChromeDriver", e);
        }
    }

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        driver.get(BROWSER_URL);
    }

    @When("I search for {string}")
    public void i_search_for(String term) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(term);
        searchBox.submit();
    }

    @Then("I should see {string} in the results")
    public void i_should_see_in_the_results(String term) {
        assert driver.getPageSource().contains(term);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
