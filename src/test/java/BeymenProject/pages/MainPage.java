package BeymenProject.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.FileInputStream;


public class MainPage {

    private WebDriver driver;



    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='ot-sdk-container']")
    public WebElement cookiesFrame;

    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookiesButton;

    @FindBy(xpath = "//*[@class='o-modal__container']")
    public WebElement selectGenderFrame;

    @FindBy(xpath = "//*[@id='genderManButton']")
    public WebElement genderManButton;

    @FindBy(xpath = "//*[@class='o-home']")
    public WebElement mainPageAd;

    @FindBy(xpath = "//*[@class='default-input o-header__search--input']")
    public WebElement mainPageSearchBox;


    public String getCellData(int rowNum, int colNum) {
          try {
            String filePath = "./src/test/resources/files/beymenProjectExcelFile.xlsx";
            FileInputStream fileInputStream=new FileInputStream(filePath);
            Workbook workBook= WorkbookFactory.create(fileInputStream);
            Sheet workSheet= workBook.getSheetAt(0);
            Cell cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
