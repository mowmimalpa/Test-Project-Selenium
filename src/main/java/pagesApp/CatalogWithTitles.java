package pagesApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static java.lang.String.valueOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CatalogWithTitles extends AbstractPage {


    @FindBy(css = "#add-title-button")
    static WebElement addNewButton;

    @FindBy(xpath = "//div[1]/label/input")
    static WebElement title;

    @FindBy(xpath = "//div[2]/label/input")
    static WebElement author;

    @FindBy(xpath = "//div[3]/label/input")
    static WebElement year;

    @FindBy(xpath = "//form /button")
    static WebElement addTitle;

    @FindBy(xpath = "//li[1]/div[2]/a/button")
    static WebElement showCopiesButton;

    @FindBy(xpath = "//li/div[2]/button[1]")
    static List<WebElement> updateButton;

    @FindBy(xpath = "//div/div/form/button")
    static WebElement editButton;

    @FindBy(xpath = "//li/div[2]/button[2]")
    static List<WebElement> removeButton;
    private final WebDriverWait wait = new WebDriverWait(driver, 10);

    public CatalogWithTitles(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CatalogWithTitles addNewTitle(String titleBook, String authorBook, int yearBook) {
        wait.until(ExpectedConditions.elementToBeClickable(addNewButton));
        addNewButton.click();
        title.sendKeys(titleBook);
        author.sendKeys(authorBook);
        year.sendKeys(valueOf(yearBook));
        addTitle.click();
        return new CatalogWithTitles(driver);
    }

    public CatalogWithTitles remove(int indexOfRemoveTitle) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(elementToBeClickable(removeButton.get(indexOfRemoveTitle-1))).click();
        return new CatalogWithTitles(driver);
    }

    public void editTitle(int indexOfRemoveTitle, String editedTitle, String editedAuthor, int editedYear) {
        wait.until(elementToBeClickable(updateButton.get(indexOfRemoveTitle -1))).click();
        title.clear();
        author.clear();
        year.clear();
        title.sendKeys(editedTitle);
        author.sendKeys(editedAuthor);
        year.sendKeys(valueOf(editedYear));
        editButton.click();
    }

    public ListOfCopies displayListOfCopies(int indexOfTitle) {
        wait.until(elementToBeClickable(showCopiesButton));
        List<WebElement> listOfBookCopies = driver.findElements(By.xpath("//div/a/button"));
        listOfBookCopies.get(indexOfTitle - 1).click();
        return PageFactory.initElements(driver, ListOfCopies.class);
    }
}
