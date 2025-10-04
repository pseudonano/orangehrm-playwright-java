package OrangeHRM.pages;

import OrangeHRM.domain.User;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;

    private final String loginForm = "xpath=//div[@class='orangehrm-login-form']";
    private final String usernameInput = "xpath=//input[@name='username']";
    private final String passwordInput = "xpath=//input[@name='password']";
    private final String loginButton = "xpath=//button[@type='submit']";


    public LoginPage(Page page) {
        this.page = page;
    }

    public void performLogin(User user){
        page.locator(loginForm).waitFor();
        page.locator(usernameInput).fill(user.getUsername());
        page.locator(passwordInput).fill(user.getPassword());
        page.locator(loginButton).click();
    }

    public void assertSuccessfulLogin(){
        page.waitForURL("**/dashboard/**");
        assertThat(page).hasTitle("OrangeHRM");
    }

    public void assertInvalidLogin(){
        assertThat(page.locator("xpath=//span[normalize-space('Required')]")).isVisible();
    }

    public void assertEmptyLogin(){
        assertThat(page.getByRole(AriaRole.ALERT)).isVisible();
    }
}
