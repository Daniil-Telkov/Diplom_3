import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PasswordRecoveryPage;
import pageObject.RegistrationPage;
import ru.yandex.practicum.stellarburgers.api.model.User;

import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.stellarburgers.api.config.APIConfig.*;
import static ru.yandex.practicum.stellarburgers.api.cridentials.UserCredentials.*;
import static ru.yandex.practicum.stellarburgers.api.utils.RequestUtils.*;

public class LoginTests {
    private WebDriver driver;
    @Before
    public void setUp() {
        defaultUserCreate();

        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @After
    public void teardown() {
        defaultUserDelete();
        driver.quit();
    }
    @DisplayName("Вход через кнопку в форме регистрации")
    @Test
    public void checkLoginFromRegistrationPage() {
        driver.get(REGISTRATION_URL);

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.loginButtonClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginFieldsFill(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);
        loginPage.loginButtonClick();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadingMainPage();
        assertTrue(mainPage.isMainHeaderDisplayed());
    }

    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной»")
    @Test
    public void checkLoginFromMainPageLoginButton() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.loginButtonClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginFieldsFill(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);
        loginPage.loginButtonClick();

        mainPage.waitForLoadingMainPage();
        assertTrue(mainPage.isMainHeaderDisplayed());
    }

    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Test
    public void checkLoginFromAccountButton() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.accountButtonClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginFieldsFill(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);
        loginPage.loginButtonClick();

        mainPage.waitForLoadingMainPage();
        assertTrue(mainPage.isMainHeaderDisplayed());
    }

    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Test
    public void checkLoginFromPasswordRecoveryPage() {
        driver.get(BASE_URL);

        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.loginButtonClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginFieldsFill(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);
        loginPage.loginButtonClick();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadingMainPage();
        assertTrue(mainPage.isMainHeaderDisplayed());
    }
}
