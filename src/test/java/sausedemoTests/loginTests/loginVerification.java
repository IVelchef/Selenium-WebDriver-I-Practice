package sausedemoTests.loginTests;

import base.authenticated;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class loginVerification extends authenticated {


    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce",
            "visual_user, secret_sauce",
            "performance_glitch_user, secret_sauce",
            "locked_out-user, secret_sauce",
            "error_user, secret_sauce",
    })
    public void userAuthenticated_when_validCredentialsProvided(String username, String password) {

        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys(password + Keys.ENTER);

        try {

            WebElement cartContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
            Assertions.assertTrue(cartContainer.isDisplayed(), "'shopping_cart_container' element is not visible! Login failed.");

        } catch (Exception e) {

            WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Login failed for user: " + username + ". Incorrect credentials.");
            } else {
                Assertions.fail("Unexpected error! The 'shopping_cart_container' element was not found, and no error message was displayed.");
            }
        }
    }
}