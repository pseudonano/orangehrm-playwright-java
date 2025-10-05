package OrangeHRM.base;

import OrangeHRM.utils.ConfigReader;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        context.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSources(true).setScreenshots(true));

        page = context.newPage();
        page.navigate(ConfigReader.get("baseUrl"),new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    @AfterEach
    void tearDown(TestInfo testInfo){
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String name = testInfo.getDisplayName().replaceAll("[^a-zA-Z0-9-_]", "_");
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces/" + name + "_" + timestamp + ".zip")));
        context.close();
    }

    @AfterAll
    static void globalTearDown(){
        browser.close();
        playwright.close();
    }
}
