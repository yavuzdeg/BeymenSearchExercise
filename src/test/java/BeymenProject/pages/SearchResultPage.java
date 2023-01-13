package BeymenProject.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SearchResultPage {

    private WebDriver driver;

    public int productIndex;

    public String price;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='o-productList__item']")
    public List<WebElement> productList;

    @FindBy(xpath = "//*[@class='m-productCard__title']")
    public List<WebElement> productTitles;

    @FindBy(xpath = "//*[@class='m-productCard__desc']")
    public List<WebElement> productDescriptions;

    @FindBy(xpath = "//*[@class='m-productCard__newPrice']")
    public List<WebElement> productPrices;

    public String randomProductInfo() throws InterruptedException {

        Thread.sleep(2000);
        Random random = new Random();
        productIndex = random.nextInt(48);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productTitles.get(productIndex));

        String title = productTitles.get(productIndex).getText();
        String description = productDescriptions.get(productIndex).getText();
        price = productPrices.get(productIndex).getText();

        return title + " " + description + " " + price;
    }


    public void writeToTxt(String productInfo) {

        try {
            File file = new File("./src/test/resources/files/beymen.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(productInfo);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickSelectedProductDescription() throws InterruptedException {

        Thread.sleep(1000);

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", productDescriptions.get(productIndex));



    }




}
