package pagesApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RentalHistory extends AbstractPage {


    @FindBy(xpath = "//body/div/div/div/button")
    static WebElement rentalCopyButton;

    @FindBy(xpath = "//form/button")
    static WebElement confirmRentalButton;

    @FindBy(xpath = "//form/div/label/input")
    static WebElement customerTitleField;

    @FindBy(xpath = "//ul/li/div/button[1]")
    static WebElement editCopyOfRental;

    @FindBy(xpath = "//ul/li/div/button[2]")
    static List<WebElement> removeButtonOfRentalCopies;

    @FindBy(css = "#return-button")
    static WebElement returnButtonOfRentalCopies;

    public RentalHistory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }
    public void addBookToRentalCatalog(String client) throws InterruptedException{
        Thread.sleep(1000);
        rentalCopyButton.click();
        customerTitleField.sendKeys(client);
        confirmRentalButton.click();
    }

    public void updateTitleOfBook(String client) throws InterruptedException {
        Thread.sleep(1000);
        editCopyOfRental.click();
        customerTitleField.clear();
        customerTitleField.sendKeys(client);
        confirmRentalButton.click();
    }
    public ListOfCopies returnToList(){
        returnButtonOfRentalCopies.click();
        return PageFactory.initElements(driver, ListOfCopies.class);
    }
    public void removeBookRental(int rentalIndex) throws InterruptedException{
        Thread.sleep(1000);
        removeButtonOfRentalCopies.get(rentalIndex -1).click();
    }
}
