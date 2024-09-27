package sausedemoTests.productTests;
import base.authenticated;
import enums.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class productsTest extends authenticated {

    @BeforeEach
    public void beforeTests() {
        driver = startBrowser(BrowserTypes.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        authenticateWithUser("standard_user", "secret_sauce");

    }

    @Test
    public void addProduct_by_name(){

        var product = getProductByTitle("Sauce Labs Backpack");
        product.findElement(By.className("btn_inventory")).click();
        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
        Assertions.assertTrue(removeButton.isDisplayed(),"Remove button is not visible for Sauce Labs Backpack.");

        var secondProduct = getProductByTitle("Sauce Labs Onesie");
        secondProduct.findElement(By.className("btn_inventory")).click();
        WebElement secondRemoveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-onesie")));
        Assertions.assertTrue(secondRemoveButton.isDisplayed(),"remove-sauce-labs-onesie");

        driver.findElement(By.id("shopping_cart_container")).click();
        WebElement cartBadge = driver.findElement(By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']"));
        int itemCount = Integer.parseInt(cartBadge.getText());
        Assertions.assertTrue(itemCount >= 2, "Item count in the cart should be exactly 2, but it was: " + itemCount);


        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));
        Assertions.assertEquals(2, cartItems.size(),"Item count in the cart should be exactly 2, but it was: " + itemCount);
        List<String> expectedProducts = List.of("Sauce Labs Backpack", "Sauce Labs Onesie");
        for (WebElement item : cartItems) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();
            Assertions.assertTrue(expectedProducts.contains(itemName), "The item in the cart is not expected: " + itemName);
        }
    }

    @Test
    public void findAllProducts(){
        var productList = getAllProducts();
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart(){

    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation(){

    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm(){

    }
}








