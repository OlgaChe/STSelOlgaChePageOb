import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by olga on 19.04.17.
 */
public class HomeworkNineteen {

    public boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebDriver driver;
    public WebDriverWait wait;

    String os = System.getProperty("os.name").toLowerCase();

    File dir = new File("src");
    File chw = new File(dir, "chromedriver.exe");

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", String.valueOf(chw));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addToCart(){
        driver.navigate().to("http://localhost/litecart/");

        for (int i = 0; i < 3; i++) {
            List<WebElement> ducks = driver.findElements(By.xpath(".//*[@id='box-most-popular']/div/ul/li"));

            ducks.get(i).click();

            String cartQuantity = driver.findElement(By.xpath(".//*[@id='cart']/a[@class='content']/span[@class='quantity']")).getText();

            try {
                WebElement size = driver.findElement(By.name("options[Size]"));
                Select select = new Select(size);
                select.selectByValue("Small");
            } catch (NoSuchElementException e) {

            }

            driver.findElement(By.name("add_cart_product")).click();

            wait.until(not(textToBePresentInElementLocated(By.xpath(".//*[@id='cart']/a[@class='content']/span[@class='quantity']"), cartQuantity)));

            driver.navigate().back();
        }

        driver.findElement(By.cssSelector(".link[href*='checkout']")).click();

        while (true) {
            WebElement element = null;
            try {
                element = driver.findElement(By.id("box-checkout-cart"));
            } catch (NoSuchElementException ex){
                break;
            }
            if (element.isDisplayed() == false) {
                break;
            }

            String productName = driver.findElement(By.cssSelector(".item>form>div>p>a>strong")).getText();

            driver.findElement(By.name("remove_cart_item")).click();

            wait.until(stalenessOf(driver.findElement(By.xpath(".//*[@id='order_confirmation-wrapper']/table/tbody/tr/td[contains(text(),'"+ productName + "')]"))));

        }
    }

    /*    @After
    public void stop() {
        driver.quit();
    }*/
}
