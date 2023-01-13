package BeymenProject.testClasses;

import BeymenProject.pages.MainPage;
import BeymenProject.pages.ProductPage;
import BeymenProject.pages.SearchResultPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;


public class TestBeymen1 {


    WebDriver driver;

    private static final Logger logger = LogManager.getLogger(TestBeymen1.class);


    @Before
    public void setUp() throws InterruptedException {

        BasicConfigurator.configure();
        logger.info("Driver initialized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void searchTest() throws InterruptedException, IOException {

        MainPage mainPage = new MainPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ProductPage productPage = new ProductPage(driver);

        logger.info("Browser opened");
        driver.get("https://www.beymen.com/");
        Thread.sleep(2000);

        logger.info("Cookies and gender options");

        if(mainPage.cookiesFrame.isDisplayed()){
            mainPage.acceptCookiesButton.click();
        }

        if(mainPage.selectGenderFrame.isDisplayed()){
            mainPage.genderManButton.click();
        }

        Assert.assertTrue(mainPage.mainPageAd.isDisplayed());

        logger.info("Beymen main page ready");

        mainPage.mainPageSearchBox.sendKeys(mainPage.getCellData(0,0));

        logger.info("First word from excel sheet sent to the searchbox");

        Thread.sleep(1000);

        mainPage.mainPageSearchBox.sendKeys(Keys.CONTROL + "a");
        mainPage.mainPageSearchBox.sendKeys(Keys.DELETE);

        logger.info("Searchbox cleared");

        mainPage.mainPageSearchBox.sendKeys(mainPage.getCellData(0,1));

        mainPage.mainPageSearchBox.sendKeys(Keys.ENTER);

        logger.info("Second word from excel sheet sent to the searchbox and enter hit");

        searchResultPage.writeToTxt(searchResultPage.randomProductInfo());

        logger.info("Product info written in txt file");

        searchResultPage.clickSelectedProductDescription();

        productPage.addToTheCart();

        logger.info("Product added to the cart");

        Thread.sleep(1000);

        productPage.goToProductBasketButton.click();

        logger.info("Basket is seen");

        Thread.sleep(1000);

        Assert.assertEquals(searchResultPage.price, productPage.basketPrice.getText());

        logger.info("Product list page price of the product and the basket price confirmed equal");

        productPage.increaseAmountToTwo();

        Thread.sleep(1000);

        Assert.assertTrue(productPage.confirmTwoProducts());

        logger.info("After the quantity is increased to two, it is confirmed that there two products in the basket");

        productPage.removeProductsButton.click();

        Thread.sleep(500);

        Assert.assertTrue(productPage.basketEmptyMessage.isDisplayed());

        logger.info("Products removed from the basket and the empty basket is confirmed");
    }

}