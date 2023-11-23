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
import pageObject.RegistrationPage;
import ru.yandex.practicum.stellarburgers.api.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.stellarburgers.api.config.APIConfig.*;
import static ru.yandex.practicum.stellarburgers.api.cridentials.UserCredentials.*;
import static ru.yandex.practicum.stellarburgers.api.utils.RequestUtils.*;

public class RegistrationTests {
    private WebDriver driver;
    Boolean clearCreatedUser;
    @Before
    public void setUp() {
        clearCreatedUser = true;
        RestAssured.baseURI = BASE_URL;

        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(REGISTRATION_URL);
    }

    @After
    public void teardown() {
        if (clearCreatedUser) {
            defaultUserDelete();
        }
        driver.quit();
    }
    @DisplayName("Проверка успешной регистрации")
    @Test
    public void checkRegistrationSuccess() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationFieldsFill(DEFAULT_USER_NAME, DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);
        registrationPage.registrationButtonClick();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadingLoginPage();
        assertTrue(loginPage.isLoginHeaderDisplayed());
    }

    @DisplayName("Проверка валидации некорректного пароля")
    @Test
    public void checkRegistrationFailedIncorrectPassword() {
        clearCreatedUser = false;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationFieldsFill(DEFAULT_USER_NAME, DEFAULT_USER_EMAIL, INCORRECT_USER_PASSWORD);
        registrationPage.registrationButtonClick();

        assertTrue(registrationPage.isIncorrectPassMessageDisplayed());
    }
}
