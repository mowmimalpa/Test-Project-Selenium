package user;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagesApp.MainPage;
import pagesApp.RegistrationPage;


import java.util.concurrent.TimeUnit;

public class TestLoginAndCreateUser {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void checkingTheDriver(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        wait = new WebDriverWait(driver,10);

    }
    @After
    public void closeTheDriver(){
        driver.close();
    }

    @Test
    public void shouldRegisterUser(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        RegistrationPage registrationPage = mainPage.redirectToRegistration();
        String randomLogin = registrationPage.generateRandomUser(4,16);
        String randomPassword = registrationPage.generateRandomUser(4,16);
        registrationPage.enterData(randomLogin,randomPassword).clickSignupButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#app > div > form > div.alert.alert--success > p")));
        String successfullyRegisteredMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div/form/div[1]/p")).getText();
        Assertions.assertEquals("You have been successfully registered!",successfullyRegisteredMessage);
    }

    @Test
    public void shouldLoginCreatedUser(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        mainPage.loggedUser("project","tester");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-title-button")));
        String visibleAddNewButton  = driver.findElement(By.cssSelector("#add-title-button")).getText();
        Assertions.assertEquals("ADD NEW",visibleAddNewButton);
    }

    @Test
    public void shouldNotLoginIfUserNotExist(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage.loggedUser("userhehe","nothehe");
        String visibleFailedLoginMessage = driver.findElement(By.xpath("//div/form/div[1]/p")).getText();
        Assertions.assertEquals("Login failed",visibleFailedLoginMessage);
    }

    @Test
    public void shouldNotRegisterWhenTheUserExists(){
        MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
        RegistrationPage registrationPage = mainPage.redirectToRegistration();
        registrationPage.enterData("project","tester").clickSignupButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p")));
        String userAlreadyExistMessage = driver.findElement(By.xpath("//div/p")).getText();
        Assertions.assertEquals("This user already exist!",userAlreadyExistMessage);
    }

    @Test
    public void shouldNotLoginWhenUserTypeWrongPassword() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.loggedUser("project","tester12");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String failedLoginMessage = driver.findElement(By.xpath("//div/form/div[1]/p")).getText();
        Assertions.assertEquals("Login failed",failedLoginMessage);
    }
}
