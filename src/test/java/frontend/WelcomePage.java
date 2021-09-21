package frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WelcomePage {
    protected WebDriver driver;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    private WebElement headerText;

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    public String getHeaderText() {
        return headerText.getText();
    }

    public void verifyUrl() {
        assertThat(driver.getCurrentUrl().endsWith("/welcome"), is(true));
    }
}
