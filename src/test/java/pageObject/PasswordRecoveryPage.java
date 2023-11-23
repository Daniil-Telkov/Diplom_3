package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage {
    private WebDriver driver;

    // Кнопка Войти
    private By loginButton = By.xpath(".//a[@href = '/account']");
    public PasswordRecoveryPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Нажатие кнопки входа на странице восстановления пароля")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

}
