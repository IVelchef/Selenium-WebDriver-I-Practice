package base;

import enums.BrowserTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class authenticated {


    public static WebDriver driver;
    public static WebDriverWait wait;

    @AfterEach
    public void afterTest() {
        driver.quit();
    }




    protected static WebDriver startBrowser(BrowserTypes browserType) {
        // Setup Browser
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
        }

        return null;
    }
    protected static void authenticateWithUser(String username, String password){
        //data-test="username"
        WebElement usernameField = driver.findElement(By.xpath("//input[@data-test='username']"));
        //data-test="password"
        WebElement passwordField = driver.findElement(By.xpath("//input[@data-test='password']"));
        //data-test="login-button"
        WebElement loginBtn = driver.findElement(By.xpath("//input[@data-test='login-button']"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();

        WebElement cartContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
        Assertions.assertTrue(cartContainer.isDisplayed(), "'shopping_cart_container' element is not visible! Login successful.");
    }

    protected WebElement getProductByTitle(String title) {
        return driver.findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", title)));
    }

    protected List<WebElement> getAllProducts(){
        return driver.findElements(By.xpath("//div[@data-test='inventory-item']"));
    }

    protected static void logout (){

        WebElement burgerBtn = driver.findElement(By.xpath("//div[@class='bm-burger-button']"));
        burgerBtn.click();

        WebElement logoutBtn = driver.findElement(By.xpath("//a[@data-test='logout-sidebar-link']"));
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        logoutBtn.click();
    }
}