package pages;

import jdk.tools.jmod.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagesApp.CatalogWithTitles;
import pagesApp.MainPage;

import java.util.concurrent.TimeUnit;

public class CatalogWithTitlesTest {

    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    CatalogWithTitles catalogWithTitles;

    @Test
    @Before
    public void shouldLogIn() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        wait = new WebDriverWait(driver, 10);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        catalogWithTitles = mainPage.loggedUser("project", "tester");
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    @Test
    public void shouldAddBook() {
        catalogWithTitles.addNewTitle("How to Build a Car", "Adrian Newey", 2017);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String author = driver.findElement(By.xpath("//li/div[1]/div[1]")).getText();
        Assertions.assertEquals("HOW TO BUILD A CAR", author);
        catalogWithTitles.remove(1);
    }

    @Test
    public void shouldUpdateBookData() throws InterruptedException {
        catalogWithTitles.addNewTitle("How to Build a Car", "Adrian Newey", 2017);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        catalogWithTitles.editTitle(1, "Life to the Limit", "Jenson Button", 2018);
        Thread.sleep(2000);
        String editedBookTitle = driver.findElement(By.xpath("//div/div/ul/li[1]/div/div[1]")).getText();
        Assertions.assertEquals("LIFE TO THE LIMIT", editedBookTitle);
        catalogWithTitles.remove(1);
    }
}






