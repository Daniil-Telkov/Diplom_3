import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.MainPage;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.stellarburgers.api.config.APIConfig.BASE_URL;
import static ru.yandex.practicum.stellarburgers.api.utils.RequestUtils.defaultUserDelete;

public class ConstructorTests {
    private WebDriver driver;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get(BASE_URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }
    @DisplayName("Переход из личного кабинета в конструктор")
    @Test
    public void constructorOpenByButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.accountButtonClick();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.constructorButtonClick();

        assertTrue(mainPage.isMainHeaderDisplayed());
    }

    @DisplayName("Переход из личного кабинета в конструктор через логотип")
    @Test
    public void constructorOpenByLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.accountButtonClick();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.logoButtonClick();

        assertTrue(mainPage.isMainHeaderDisplayed());
    }
    @DisplayName("Переход к разделу Булки")
    @Test
    public void bunSectionOpenTest() {
        MainPage mainPage = new MainPage(driver);

        assertTrue(mainPage.isBunHeaderDisplayed());
        assertThat(mainPage.getBunSectionClassAttribute(), CoreMatchers.containsString("tab_tab_type_current"));
    }

    @DisplayName("Переход к разделу Соусы")
    @Test
    public void sauceSectionOpenTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.sauceSectionClick();

        assertTrue(mainPage.isSauceHeaderDisplayed());
        assertThat(mainPage.getSauceSectionClassAttribute(), CoreMatchers.containsString("tab_tab_type_current"));
    }

    @DisplayName("Переход к разделу Начинки")
    @Test
    public void fillingSectionOpenTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.fillingSectionClick();

        assertTrue(mainPage.isFillingHeaderDisplayed());
        assertThat(mainPage.getFillingSectionClassAttribute(), CoreMatchers.containsString("tab_tab_type_current"));
    }

}
