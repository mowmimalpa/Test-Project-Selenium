package pagesApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
    @FindBy(css = "#login")
    static WebElement login;
    @FindBy(css = "#password")
    static WebElement password;
    @FindBy(css = "#login-btn")
    static WebElement logInButton;
    @FindBy(css = "#register-btn")
    static WebElement signUpButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public RegistrationPage redirectToRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable((signUpButton)));
        signUpButton.click();
        return PageFactory.initElements(driver, RegistrationPage.class);
    }


    public CatalogWithTitles loggedUser(String project,String tester) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        login.sendKeys("project");
        password.sendKeys("tester");
        logInButton.click();
        return PageFactory.initElements(driver,CatalogWithTitles.class);
    }
}
