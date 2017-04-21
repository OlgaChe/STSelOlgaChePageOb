package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Olga Cherniak on 4/21/2017.
 */
public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath=".//*[@id='box-most-popular']/div/ul/li")
    public List<WebElement> ducksList;

    @FindBy(xpath = ".//*[@id='cart']/a[@class='content']/span[@class='quantity']")
    public WebElement cartQuantityLabel;

    @FindBy(css = ".link[href*='checkout']")
    public WebElement checkoutButton;

    public void open(){
        driver.navigate().to("http://localhost/litecart/");
    }
}
