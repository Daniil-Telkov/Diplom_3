import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.MainPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.stellarburgers.api.config.APIConfig.*;
import static ru.yandex.practicum.stellarburgers.api.cridentials.UserCredentials.DEFAULT_USER_EMAIL;
import static ru.yandex.practicum.stellarburgers.api.cridentials.UserCredentials.DEFAULT_USER_PASSWORD;
import static ru.yandex.practicum.stellarburgers.api.utils.RequestUtils.defaultUserCreate;
import static ru.yandex.practicum.stellarburgers.api.utils.RequestUtils.defaultUserDelete;

public class AccountTests {
    private WebDriver driver;
    @Before
    public void setUp() {
        defaultUserCreate();

        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);

    }

    @After
    public void teardown() {
        defaultUserDelete();
        driver.quit();
    }
    @DisplayName("Переход по клику на «Личный кабинет")
    @Test
    public void accountOpenTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.accountButtonClick();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitForLoadingAccountPage();

        assertTrue(accountPage.isSignOutButtonDisplayed());
    }

    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Test
    public void logOutTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.accountButtonClick();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitForLoadingAccountPage();
        accountPage.signOutButtonClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadingLoginPage();
        assertTrue(loginPage.isLoginHeaderDisplayed());
    }

}
