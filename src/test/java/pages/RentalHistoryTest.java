package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagesApp.CatalogWithTitles;
import pagesApp.ListOfCopies;
import pagesApp.MainPage;
import pagesApp.RentalHistory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RentalHistoryTest {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    CatalogWithTitles catalogWithTitles;
    RentalHistory rentalHistory;
    ListOfCopies listOfCopies;

    @Before
    public void shouldAddNewItem() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        wait = new WebDriverWait(driver, 10);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        catalogWithTitles = mainPage.loggedUser("project", "tester");
        catalogWithTitles.addNewTitle("TestTitle", "TestAuthor", 1990);
        listOfCopies = catalogWithTitles.displayListOfCopies(1);
        listOfCopies.addCopyToList();
        rentalHistory = listOfCopies.showRentalHistoryOfCopies();

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void shouldAddRentalBookToCatalog() throws InterruptedException {
        rentalHistory.addBookToRentalCatalog("RENTALTEST");
        String client = driver.findElement(By.xpath("//ul/li/div/div[1]")).getText();
        Assertions.assertEquals("RENTALTEST", client);
    }

    @Test
    public void shouldUpdateRentalBookInformation() throws InterruptedException {
        rentalHistory.addBookToRentalCatalog("RENTALTEST");
        rentalHistory.updateTitleOfBook("UPDATERENTAL");

        String updateInformation = driver.findElement(By.xpath("//ul/li/div/div[1]")).getText();
        Assertions.assertEquals("UPDATERENTAL", updateInformation);

    }

    @Test
    public void shouldRemoveBookFromRentalCatalog() throws InterruptedException {
        rentalHistory.addBookToRentalCatalog("RENTALTEST");
        rentalHistory.removeBookRental(1);

        wait.until(visibilityOfElementLocated(By.xpath("//div/div/div/div[2]/p")));
        String rentalHistoryInformation = driver.findElement(By.xpath("//div/div/div/div[2]/p")).getText();
        Assertions.assertEquals("No rents...", rentalHistoryInformation);
    }

    @Test
    public void shouldNotRentalBookIfTwoPersonsRentAthTheSameTime() throws InterruptedException {


    rentalHistory.addBookToRentalCatalog("CLIENT");
    rentalHistory.addBookToRentalCatalog("CUSTOMER");
    Thread.sleep(1000);
    List<WebElement> historyOfRent = driver.findElements(By.xpath("//ul/li/div/div[2]"));

    String clientDate = historyOfRent.get(0).getText();
    String customerDate = historyOfRent.get(1).getText();
    Assertions.assertNotEquals(clientDate,customerDate);

}

}
