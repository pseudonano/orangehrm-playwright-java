package OrangeHRM.base;

import OrangeHRM.utils.ConfigReader;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void globalSetup(){
        playwright = Playwright.create();

        String browserName = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        if("chromium".equalsIgnoreCase(browserName)){
            browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(headless));
        } else if ("firefox".equalsIgnoreCase(browserName)) {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        } else if ("webkit".equalsIgnoreCase(browserName)) {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        } else {
            throw new IllegalArgumentException("Unsupported browser: "+browserName);
        }
    }

    @BeforeEach
    void setUp(){
        context = browser.newContext();
        page = context.newPage();
        page.navigate(ConfigReader.get("baseUrl"));
    }

    @AfterEach
    void tearDown(){
        context.close();
    }

    @AfterAll
    static void globalTearDown(){
        browser.close();
        playwright.close();
    }
}
