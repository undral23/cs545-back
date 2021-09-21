package frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductFormPage {
    protected WebDriver driver;

    public ProductFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtProductNumber")
    private WebElement txtProductNumber;

    @FindBy(id = "txtName")
    private WebElement txtName;

    @FindBy(id = "txtPrice")
    private WebElement txtPrice;

    @FindBy(id = "txtDescription")
    private WebElement txtDescription;

    @FindBy(id = "txtNumberInStock")
    private WebElement txtNumberInStock;

    @FindBy(id = "btnSubmit")
    private WebElement btnSubmit;

    @FindBy(id = "btnCancel")
    private WebElement btnCancel;

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    public void fillForm(String productNumber, String name, double price, String description, int numberInStock) {
        this.txtProductNumber.clear();
        this.txtProductNumber.sendKeys(productNumber);

        this.txtName.clear();
        this.txtName.sendKeys(name);

        this.txtPrice.clear();
        this.txtPrice.sendKeys(String.valueOf(price));

        this.txtDescription.clear();
        this.txtDescription.sendKeys(description);

        this.txtNumberInStock.clear();
        this.txtNumberInStock.sendKeys(String.valueOf(numberInStock));

    }

    public boolean isValid() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String invalidBorderColor = "rgb(220, 53, 69)";
        boolean inValid = invalidBorderColor.equals(this.txtProductNumber.getCssValue("border-color"))
                || invalidBorderColor.equals(this.txtName.getCssValue("border-color"))
                || invalidBorderColor.equals(this.txtPrice.getCssValue("border-color"))
                || invalidBorderColor.equals(this.txtDescription.getCssValue("border-color"))
                || invalidBorderColor.equals(this.txtNumberInStock.getCssValue("border-color"));
        return inValid == false;
    }

    public void clickSubmit() {
        btnSubmit.click();
    }
}
