package frontend;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FrontEndTest {
    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Batdelger\\tools\\chromedriver.exe");
    }

    @AfterClass
    public static void closeTheBrowser() {
    }

    @Test
    public void testProductsPageCreateButton() {
        ProductsPage productsPage = new ProductsPage(new ChromeDriver());
        productsPage.open("http://localhost:3000/products");
        productsPage.clickCreateProduct();
        assertThat(productsPage.driver.getCurrentUrl(), is("http://localhost:3000/products/new"));
        productsPage.close();
    }

    @Test
    public void testProductsPageEditButton() {
        ProductsPage productsPage = new ProductsPage(new ChromeDriver());
        productsPage.open("http://localhost:3000/products");
        ProductFormPage productFormPage = productsPage.clickEditButton("P00001");
        assertThat(productFormPage.driver.getCurrentUrl(), is("http://localhost:3000/products/P00001"));

        productFormPage.close();
    }

    @Test
    public void testProductsPageDeleteButton() {
        ProductsPage productsPage = new ProductsPage(new ChromeDriver());
        productsPage.open("http://localhost:3000/products");
        productsPage.clickDeleteButton("P00001");

        WebDriverWait wait = new WebDriverWait(productsPage.driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = productsPage.driver.switchTo().alert();

        assertThat(alert.getText(), is("Are you sure to delete?"));
        alert.dismiss();
        productsPage.close();
    }

    @Test
    public void testProductFormPageFillInvalidData() {
        ProductFormPage productFormPage = new ProductFormPage(new ChromeDriver());
        productFormPage.open("http://localhost:3000/products/new");
        productFormPage.fillForm("", "", 0.0, "", 0);
        productFormPage.clickSubmit();


        assertThat(productFormPage.isValid(), is(false));
        productFormPage.close();
    }

    @Test
    public void testProductFormPageFillValidData() {
        ProductFormPage productFormPage = new ProductFormPage(new ChromeDriver());
        productFormPage.open("http://localhost:3000/products/new");
        productFormPage.fillForm("T0001", "Test T0001", 100.0, "Desc", 10);
        productFormPage.clickSubmit();

        WebDriverWait wait = new WebDriverWait(productFormPage.driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = productFormPage.driver.switchTo().alert();

        alert.dismiss();
        assertThat(productFormPage.isValid(), is(true));
        assertThat(productFormPage.driver.getCurrentUrl(), is("http://localhost:3000/products/T0001"));

        productFormPage.close();
    }

    @Test
    public void testBuyProductWithValidQty() {
        BuyProductPage buyProductPage = new BuyProductPage(new ChromeDriver());
        buyProductPage.open("http://localhost:3000/");
        buyProductPage.buyProduct("T0001", 1);

        WebElement cartBadge = buyProductPage.driver.findElement(By.cssSelector("#navbarCollapse > ul > li:nth-child(4) > a > span"));
        assertThat(cartBadge.getText(), is("1"));
        buyProductPage.close();
    }

    @Test
    public void testBuyProductWithInValidQty() {
        BuyProductPage buyProductPage = new BuyProductPage(new ChromeDriver());
        buyProductPage.open("http://localhost:3000/");
        buyProductPage.buyProduct("T0001", 10000000);

        WebDriverWait wait = new WebDriverWait(buyProductPage.driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = buyProductPage.driver.switchTo().alert();

        assertThat(alert.getText(), is("Quantity unavailable"));
        alert.accept();

        buyProductPage.close();
    }
}