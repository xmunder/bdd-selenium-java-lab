package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Hooks {
    private static final String REMOTE_URL = "http://selenium:4444/wd/hub";
    private static final String WIDTH = "1920";
    private static final String HEIGHT = "1080";
    private static final String LANG = "es-ES";
    private static final String ACCEPT_LANG = "es-ES,es";
    private static final boolean HEADLESS = true;

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            if (HEADLESS) {
                options.addArguments("--headless");
            }
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--lang=" + LANG);
            options.addArguments("--accept-lang=" + ACCEPT_LANG);
            options.addArguments("--window-size=" + WIDTH + "," + HEIGHT);

            WebDriver driver = new RemoteWebDriver(new URL(REMOTE_URL), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            context.setDriver(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium remote URL", e);
        }
    }

    @After
    public void tearDown() {
        WebDriver driver = context.getDriver();
        if (driver != null) {
            driver.quit();
            context.setDriver(null);
        }
    }
}
