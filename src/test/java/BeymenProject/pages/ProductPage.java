package BeymenProject.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductPage {

    private WebDriver driver;

    public String onePrice;
    public String twoPrice;

    SearchResultPage searchResultPage = new SearchResultPage(driver);

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='m-variation__item -criticalStock']")
    public List<WebElement> criticalQuantitySize;

    @FindBy(xpath = "//*[@class='m-variation__item']")
    public List<WebElement> normalQuantitySize;

    @FindBy(xpath = "//*[@id='addBasket']")
    public WebElement addBasketButton;

    public void addToTheCart() throws InterruptedException {

        if(normalQuantitySize.size()>0){
            for(WebElement element : normalQuantitySize){
                element.click();
                break;
            }
        }else{
            for(WebElement element : criticalQuantitySize){
                element.click();
                break;
            }
        }
        Thread.sleep(500);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", addBasketButton);
        Thread.sleep(2000);
    }

    @FindBy(xpath = "//*[@class='icon icon-cart icon-cart-active']")
    public WebElement goToProductBasketButton;

    @FindBy(xpath = "//*[@class='m-orderSummary__item -grandTotal']//*[@class='m-orderSummary__value']")
    public WebElement basketPrice;

    @FindBy(xpath = "//*[@class='a-selectControl -small']")
    public WebElement productQuantityDropdown;

    @FindBy(xpath = "//*[@class='m-productPrice__salePrice']")
    public WebElement productSalePrice;

    public void increaseAmountToTwo() throws InterruptedException {

        onePrice = productSalePrice.getText();
        Select quantity = new Select(productQuantityDropdown);

        quantity.selectByVisibleText("2 adet");
        Thread.sleep(500);
        twoPrice = productSalePrice.getText();
    }

    @FindBy(xpath = "//*[@id='quantitySelect0-key-0']")
    public WebElement productQuantity;

    public boolean confirmTwoProducts() throws InterruptedException {

        int onePriceInt = Integer.parseInt((onePrice.substring(0, onePrice.indexOf(','))).replace(".", ""));
        System.out.println(onePriceInt);

        int twoPriceInt = Integer.parseInt((twoPrice.substring(0, twoPrice.indexOf(','))).replace(".", ""));
        System.out.println(twoPriceInt);

        if(twoPriceInt == onePriceInt + onePriceInt){
            return true;
        }else{
            return false;
        }
    }

    @FindBy(xpath = "//*[@class='m-basket__remove btn-text']")
    public WebElement removeProductsButton;

    @FindBy(xpath = "//*[@class='m-empty__messageTitle']")
    public WebElement basketEmptyMessage;


}
