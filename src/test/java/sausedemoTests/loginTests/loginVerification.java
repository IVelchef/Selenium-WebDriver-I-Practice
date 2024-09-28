package sausedemoTests.loginTests;
import base.authenticated;
import enums.BrowserTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginVerification extends authenticated {
    @BeforeEach
    public void beforeTests() {
        driver = startBrowser(BrowserTypes.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }

    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce",
            "visual_user, secret_sauce",
            "performance_glitch_user, secret_sauce",
            "locked_out-user, secret_sauce",
            "error-user, secret_sauce",
    })
    public void userAuthenticated(String username, String password) {

        try {

            authenticateWithUser(username, password);

            WebElement cartContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
            Assertions.assertTrue(cartContainer.isDisplayed(), "'shopping_cart_container' element is not visible! Login successful for user: " + username);

            logout();

        } catch (Exception e) {

            System.out.println("Login failed for user: " + username );
        }
    }
}

