package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;

    // Кнопка регистрации
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Кнопка Войти
    private By loginButton = By.xpath(".//a[text() = 'Войти']");

    // поле ввода Имя
    private By nameInputField = By.xpath(".//fieldset[1]//input[@name='name']");
    // поле ввода Email
    private By emailInputField = By.xpath(".//fieldset[2]//input[@name='name']");
    // поле ввода Пароль
    private By passInputField = By.xpath(".//fieldset//input[@name='Пароль']");

    // Текст валидации пароля
    private By incorrectPass = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Заполнение полей регистрации")
    public void registrationFieldsFill(String name,String email, String password) {
        driver.findElement(nameInputField).sendKeys(name);
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passInputField).sendKeys(password);
    }

    @Step("Нажатие кнопки регистрации")
    public void registrationButtonClick() {
        driver.findElement(registrationButton).click();
    }

    @Step("Проверка наличия сообщения валидации пароля")
    public Boolean isIncorrectPassMessageDisplayed() {
        return driver.findElement(incorrectPass).isDisplayed();
    }

    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }
}
