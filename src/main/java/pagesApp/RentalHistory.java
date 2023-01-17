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
}
