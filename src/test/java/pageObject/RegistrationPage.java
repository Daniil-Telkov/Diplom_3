package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    // Кнопка регистрации
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    // Нажатие кнопки регистрации
    public void registrationButtonClick() {
        driver.findElement(registrationButton).click();
    }
}
