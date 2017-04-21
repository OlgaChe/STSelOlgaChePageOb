package app;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.DuckPage;
import pages.HomePage;

import java.io.File;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

/**
 * Created by Olga Cherniak on 4/21/2017.
 */
public class Application {

    private WebDriver driver;
    private WebDriverWait wait;

    private HomePage homePage;
    private DuckPage duckPage;
    private CartPage cartPage;

    public Application() {
        File dir = new File("src");
        File chw = new File(dir, "chromedriver.exe");

        System.setProperty("webdriver.chrome.driver", String.valueOf(chw));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        duckPage = new DuckPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addToCart() throws InterruptedException {
        homePage.open();

        for (int i = 0; i < 3; i++) {
            homePage.ducksList.get(i).click();

            String cartQuantity = homePage.cartQuantityLabel.toString();

            duckPage.selectSize();
            duckPage.addToCartButton.click();
            Thread.sleep(1000);

            driver.navigate().back();
        }

        homePage.checkoutButton.click();

        Thread.sleep(3000);

        while (true) {
            WebElement element = null;
            try {
                element = cartPage.productItem;
            } catch (NoSuchElementException ex){
                break;
            }
            if (element.isDisplayed() == false) {
                break;
            }

            cartPage.removeCartButton.click();

            cartPage.assertThatProductRemoved();
        }
    }
}
