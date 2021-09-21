package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BuyProductPage {
    protected WebDriver driver;
    private final String prefixBuyBtn = "btnBuy-";
    private final String prefixQuantityInput = "inputQuantity-";

    public BuyProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    public void buyProduct(String productNumber, int quantity) {
        WebElement inputQuantity = driver.findElement(By.id(prefixQuantityInput + productNumber));
        inputQuantity.clear();
        inputQuantity.sendKeys(String.valueOf(quantity));

        WebElement btnBuyProduct = driver.findElement(By.id(prefixBuyBtn + productNumber));
        btnBuyProduct.click();
    }
}
