package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

/**
 * Created by Olga Cherniak on 4/21/2017.
 */
public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "remove_cart_item")
    public WebElement removeCartButton;

    @FindBy(css = ".item>form>div>p>a>strong")
    public WebElement productItem;

    public void assertThatProductRemoved(){
        String productName = productItem.getText();
        wait.until(stalenessOf(driver.findElement(By.xpath(".//*[@id='order_confirmation-wrapper']/table/tbody/tr/td[contains(text(),'"+ productName + "')]"))));
    }
}
