package pages;

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
import pagesApp.ListOfCopies;
import pagesApp.MainPage;

public class ListOfCopiesTest {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    CatalogWithTitles catalogWithTitles;
    ListOfCopies listOfCopies;
    @Test
    @Before
    public void shouldAddNewItem(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        wait = new WebDriverWait(driver,10);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        catalogWithTitles = mainPage.loggedUser("project","tester");
        catalogWithTitles.addNewTitle("TestTitle","TestAuthor",1990);
        listOfCopies = catalogWithTitles.displayListOfCopies(1);
    }
    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void shouldAddBookToListOfCopies() throws InterruptedException {
        listOfCopies.addCopyToList();
        Thread.sleep(1000);
        String index = driver.findElement(By.xpath("//ul/li/div/div[3]")).getText();
        Assertions.assertEquals("Available",index);
        listOfCopies.removeCopies(1);
        catalogWithTitles = listOfCopies.returnFromListOfCopies();
        Thread.sleep(1000);
        catalogWithTitles.remove(1);
    }
    @Test
    public void shouldRemoveCopyFromListOfCopies() throws InterruptedException{
        listOfCopies.addCopyToList();
        listOfCopies.removeCopies(1);
        Thread.sleep(1000);
        String noCopiesMessage = driver.findElement(By.xpath("//div/p")).getText();
        Assertions.assertEquals("No copies...",noCopiesMessage);
    }
}
