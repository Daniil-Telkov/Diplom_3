package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    // Кнопка Войти в аккаунт
    private By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    // Кнопка Личный кабинет
    private By accountButton = By.xpath(".//a[@href = '/account']");
    // Заголовок главной страницы
    private By mainPageHeader = By.xpath(".//h1[text() = 'Соберите бургер']");

    // Раздел Булки
    private By bunSection = By.xpath(".//div[.='Булки']");
    // Раздел Соусы
    private By sauceSection = By.xpath(".//div[.='Соусы']");
    // Раздел Начинки
    private By fillingSection = By.xpath(".//div[.='Начинки']");

    // Заголовок конструктора Булки
    private By bunHeader = By.xpath(".//h2[.='Булки']");
    // Заголовок конструктора Соусы
    private By sauceHeader = By.xpath(".//h2[.='Соусы']");
    // Заголовок конструктора Начинки
    private By fillingHeader = By.xpath(".//h2[.='Начинки']");
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Нажатие кнопки входа на главной странице")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки входа в личный кабинет на главной странице")
    public void accountButtonClick() {
        driver.findElement(accountButton).click();
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoadingMainPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(mainPageHeader));
    }

    @Step("Проверка, что появился заголовок главной страницы")
    public Boolean isMainHeaderDisplayed() {
        return driver.findElement(mainPageHeader).isDisplayed();
    }

    @Step("Клик на раздел Булки")
    public void bunSectionClick() {
        driver.findElement(bunSection).click();
    }

    @Step("Клик на раздел Соусы")
    public void sauceSectionClick() {
        driver.findElement(sauceSection).click();
    }
    @Step("Клик на раздел Начинки")
    public void fillingSectionClick() {
        driver.findElement(fillingSection).click();
    }

    @Step("Проверка отображения заголовка Булки")
    public Boolean isBunHeaderDisplayed() {
        return driver.findElement(bunHeader).isDisplayed();
    }
    @Step("Проверка отображения заголовка Соусы")
    public Boolean isSauceHeaderDisplayed() {
        return driver.findElement(sauceHeader).isDisplayed();
    }
    @Step("Проверка отображения заголовка Начинки")
    public Boolean isFillingHeaderDisplayed() {
        return driver.findElement(fillingHeader).isDisplayed();
    }

    @Step("Получение аттрибута class вкладки Булки")
    public String getBunSectionClassAttribute() {
        return  driver.findElement(bunSection).getAttribute("class");
    }
    @Step("Получение аттрибута class вкладки Соусы")
    public String getSauceSectionClassAttribute() {
        return  driver.findElement(sauceSection).getAttribute("class");
    }
    @Step("Получение аттрибута class вкладки Начинки")
    public String getFillingSectionClassAttribute() {
        return  driver.findElement(fillingSection).getAttribute("class");
    }

}
