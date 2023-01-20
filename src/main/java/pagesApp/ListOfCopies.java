package pagesApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ListOfCopies extends AbstractPage{

    @FindBy(xpath = "//*[@id=\\\"add-item-button\\\"]")
    static WebElement addItemButtonOfCopies;

    @FindBy(xpath = "//from'button")
    static WebElement addTitleButtonOfCopies;

    @FindBy(xpath = "//div/div/input")
    static WebElement purchaseDateInputField;

    @FindBy(xpath = "//li[1]/div/a/button")
    static WebElement showCopiesFromTitlesCatalog;

    @FindBy(xpath = "//ul/li/div/button[1]")
    static WebElement editButtonOfCopies;

    @FindBy(xpath = "//ul/li/div/button[2]")
    static List<WebElement> removeButtonOfCopies;

    @FindBy(xpath = "//div/div/div/div/button")
    static WebElement returnButtonFromCopies;

    @FindBy(xpath = "//div[2]/p")
    static WebElement noCopiesDisplayWindow;

    public ListOfCopies(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    public void addCopyToList(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addItemButtonOfCopies));
        addTitleButtonOfCopies.click();
    }

    public void removeCopies(int indexOfRemoveCopy) throws InterruptedException{
        Thread.sleep(1000);
        removeButtonOfCopies.get(indexOfRemoveCopy -1).click();
    }

    public CatalogWithTitles returnFromListOfCopies(){
        returnButtonFromCopies.click();
        return PageFactory.initElements(driver,CatalogWithTitles.class);
    }

    public RentalHistory showRentalHistoryOfCopies() throws  InterruptedException{
        Thread.sleep(1000);
        showCopiesFromTitlesCatalog.click();
        return PageFactory.initElements(driver,RentalHistory.class);
    }
}
