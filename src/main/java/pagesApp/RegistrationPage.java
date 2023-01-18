package pagesApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.passay.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegistrationPage extends AbstractPage{
    @FindBy (css = "#login")
    static WebElement login;

    @FindBy(css = "#password")
    static WebElement password;

    @FindBy(css = "#password-repeat")
    static WebElement repeatedPassword;

    @FindBy(css = "register-btn")
    static WebElement signUpButton;




    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    public String generateRandomUser (int minimumNumberOfCharacters, int maxNumberOfCharacters){
        Random random = new Random();
        int randomUser = random.nextInt(maxNumberOfCharacters - minimumNumberOfCharacters +1) + minimumNumberOfCharacters;
        List<CharacterRule> rules = new ArrayList<>();
        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
        rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
        String pass = new PasswordGenerator().generatePassword(randomUser,rules);
        return pass;

    }
    public RegistrationPage enterData(String user,String pass){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(repeatedPassword));
        login.sendKeys("project");
        password.sendKeys("tester");
        repeatedPassword.sendKeys("tester");
        return PageFactory.initElements(driver,RegistrationPage.class);

    }
    public CatalogWithTitles clickSignupButton(){
        signUpButton.click();
        return PageFactory.initElements(driver,CatalogWithTitles.class);

    }

}
