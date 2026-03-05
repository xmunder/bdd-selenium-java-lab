package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchSteps {
    private static final String SEARCH_URL = "https://duckduckgo.com/";

    private final TestContext context;

    public SearchSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the DuckDuckGo search page")
    public void i_am_on_the_duckduckgo_search_page() {
        driver().get(SEARCH_URL);
    }

    @When("I search for {string}")
    public void i_search_for(String term) {
        WebElement searchBox = driver().findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys(term);
        searchBox.submit();
    }

    @Then("I should see {string} in the results")
    public void i_should_see_in_the_results(String term) {
        Assert.assertTrue("Expected term was not found in page source", driver().getPageSource().contains(term));
    }

    private WebDriver driver() {
        WebDriver driver = context.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        return driver;
    }
}
