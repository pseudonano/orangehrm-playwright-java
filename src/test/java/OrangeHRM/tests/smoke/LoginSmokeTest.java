package OrangeHRM.tests.smoke;

import OrangeHRM.base.BaseTest;
import OrangeHRM.domain.User;
import OrangeHRM.pages.LoginPage;
import org.junit.jupiter.api.*;

public class LoginSmokeTest extends BaseTest {

    @DisplayName("Smoke Login")
    @Test
    void SmokeLogin(){
        User testValidUser = new User("Admin","admin123","success");
        LoginPage loginPage = new LoginPage(page);
        loginPage.performLogin(testValidUser);

        page.waitForURL("**/dashboard/**");
        Assertions.assertTrue(page.url().contains("dashboard"));  // JUnit assertion
        loginPage.assertSuccessfulLogin();
    }
}
