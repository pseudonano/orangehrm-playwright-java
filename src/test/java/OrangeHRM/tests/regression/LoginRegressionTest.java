package OrangeHRM.tests.regression;

import OrangeHRM.base.BaseTest;
import OrangeHRM.domain.User;
import OrangeHRM.pages.LoginPage;
import OrangeHRM.utils.DataLoader;
import OrangeHRM.utils.UserDataLoaderFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class LoginRegressionTest extends BaseTest {

    static List<User> users() {
        String filePath = "src/test/resources/regression_users.csv";
        DataLoader loader = UserDataLoaderFactory.getLoader(filePath);
        return loader.load(filePath);
    }

    @ParameterizedTest(name = "Login test for user {0}")
    @MethodSource("users")
    void loginRegressionTest(User user) {
        LoginPage loginPage = new LoginPage(page);

        loginPage.performLogin(user);

        if (user.getExpected().equalsIgnoreCase("SUCCESS")) {
            loginPage.assertSuccessfulLogin();
        } else if (user.getExpected().equalsIgnoreCase("EMPTY")) {
            loginPage.assertEmptyLogin();
        } else {
            loginPage.assertInvalidLogin();
        }
    }
}
