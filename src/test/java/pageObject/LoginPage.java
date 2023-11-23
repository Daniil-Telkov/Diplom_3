package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    // Кнопка Войти
    private By signInButton = By.xpath(".//button[text()='Войти']");

    // поле ввода Email
    private By emailInputField = By.xpath(".//fieldset[1]//input[@name='name']");
    // поле ввода Пароль
    private By passInputField = By.xpath(".//fieldset//input[@name='Пароль']");

    // Заголовок страницы авторизации
    private By loginHeader = By.xpath(".//h2[text() = 'Вход']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Заполнение полей для авторизации")
    public void loginFieldsFill(String email, String password) {
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passInputField).sendKeys(password);
    }

    @Step("Нажатие кнопки входа в аккаунт")
    public void loginButtonClick() {
        driver.findElement(signInButton).click();
    }

    @Step("Ожидание загрузки страницы авторизации")
    public void waitForLoadingLoginPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(loginHeader));
    }

    @Step("Проверка, что отображается заголовок \"Вход\" при успешной регистрации")
    public Boolean isLoginHeaderDisplayed() {
        return driver.findElement(loginHeader).isDisplayed();
    }

    @Step("Заполнение полей и авторизация")
    public void doLogin(String email, String password) {
        loginFieldsFill(email, password);
        loginButtonClick();
    }
}
