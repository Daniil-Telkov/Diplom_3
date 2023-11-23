package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
    private WebDriver driver;

    // Кнопка Выход
    private By signOutButton = By.xpath(".//button[text()='Выход']");
    // Кнопка Конструктор
    private By constructorButton = By.xpath(".//li//a[1][@href='/']");
    // Логотип-ссылка на конструктор
    private By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']//a[@href='/']");

    public AccountPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажатие кнопки Выход")
    public void signOutButtonClick() {
        driver.findElement(signOutButton).click();
    }

    @Step("Ожидание загрузки личного кабинета")
    public void waitForLoadingAccountPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(signOutButton));
    }

    @Step("Проверка, что отображается кнопка Выход при открытии личного кабинета")
    public Boolean isSignOutButtonDisplayed() {
        return driver.findElement(signOutButton).isDisplayed();
    }

    @Step("Нажатие кнопки Конструктор")
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на Логотип")
    public void logoButtonClick() {
        driver.findElement(logoButton).click();
    }
}
