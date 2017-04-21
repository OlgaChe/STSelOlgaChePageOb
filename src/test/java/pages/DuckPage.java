package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Olga Cherniak on 4/21/2017.
 */
public class DuckPage extends Page {

    public DuckPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "add_cart_product")
    public WebElement addToCartButton;

    public void selectSize(){
        try {
            WebElement size = driver.findElement(By.name("options[Size]"));
            Select select = new Select(size);
            select.selectByValue("Small");
        } catch (NoSuchElementException e) {

        }
    }
}
