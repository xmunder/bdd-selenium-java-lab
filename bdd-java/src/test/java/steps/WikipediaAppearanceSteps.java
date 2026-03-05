package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaAppearanceSteps {

    private static final String WIKIPEDIA_URL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
    private final TestContext context;

    public WikipediaAppearanceSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the Wikipedia home page")
    public void i_am_on_the_wikipedia_home_page() {
        getDriver().get(WIKIPEDIA_URL);
    }

    @When("I set text size to {string}")
    public void i_set_text_size_to(String sizeLabel) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(textSizeLabelLocator(sizeLabel)));
        label.click();
    }

    @Then("the selected text size should be {string}")
    public void the_selected_text_size_should_be(String sizeLabel) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(textSizeLabelLocator(sizeLabel)));
        WebElement radio = findRadioForLabel(label, sizeLabel);
        Assert.assertTrue("Expected selected text size: " + sizeLabel, radio.isSelected());
    }

    private WebElement findRadioForLabel(WebElement label, String sizeLabel) {
        String targetId = label.getAttribute("for");
        if (targetId != null && !targetId.isBlank()) {
            return getDriver().findElement(By.id(targetId));
        }

        String literal = quoteForXPath(sizeLabel);
        By inputBySibling = By.xpath(
            "//div[@id='vector-appearance']//input[@type='radio'][following-sibling::label[.//*[normalize-space()=" + literal + "] or normalize-space()=" + literal + "]]"
        );
        return getDriver().findElement(inputBySibling);
    }

    private By textSizeLabelLocator(String sizeLabel) {
        String literal = quoteForXPath(sizeLabel);
        return By.xpath(
            "//div[@id='vector-appearance']//label[.//*[normalize-space()=" + literal + "] or normalize-space()=" + literal + "]"
        );
    }

    private String quoteForXPath(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }
        String[] parts = value.split("'");
        StringBuilder concat = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                concat.append(", \"'\", ");
            }
            concat.append("'").append(parts[i]).append("'");
        }
        concat.append(")");
        return concat.toString();
    }

    private WebDriver getDriver() {
        WebDriver driver = context.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Ensure Cucumber hooks run before this step.");
        }
        return driver;
    }
}
