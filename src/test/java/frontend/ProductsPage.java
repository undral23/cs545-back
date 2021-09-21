package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    protected WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "tableMain")
    private WebElement mainTable;

    @FindBy(id = "btnCreate")
    private WebElement createButton;

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }


    public ProductFormPage clickCreateProduct() {
        createButton.click();
        return new ProductFormPage(driver);
    }

    public ProductFormPage clickEditButton(String productNumber) {
        WebElement linkProduct = driver.findElement(By.id("link-" + productNumber));
        linkProduct.click();
        return new ProductFormPage(driver);
    }

    public void clickDeleteButton(String productNumber) {
        WebElement btnDeleteProduct = driver.findElement(By.id("btnDelete-" + productNumber));
        btnDeleteProduct.click();
    }
}
