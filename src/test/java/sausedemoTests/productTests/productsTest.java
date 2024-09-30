package sausedemoTests.productTests;
import base.authenticated;
import enums.BrowserTypes;
import org.junit.jupiter.api.Assertions;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        authenticateWithUser("standard_user", "secret_sauce");

    }

@Test
    public void VerifyAddProductsByNameInCart () {

    List<String> addedProductNames = addProductsToCart();

    goToShoppingCart ();

    List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

    for (String productName : addedProductNames) {

        Assertions.assertTrue(cartItems.stream().anyMatch(item -> item.getText().contains(productName)),
                "The product " + productName + " is not in the cart.");
    }
}

    @Test
    public void VerifyAddProductsByIndexInCart() {

        List<String> addedProductNames = addProductsToCart();

        goToShoppingCart();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assertions.assertEquals(2, cartItems.size(), "Item count in the cart should be exactly 2.");

        WebElement cartBadge = driver.findElement(By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']"));
        int itemCount = Integer.parseInt(cartBadge.getText());

        Assertions.assertEquals(2, itemCount, "Shopping cart badge count should be 2.");


        logout ();

    }

    @Test
    public void productAddedToShoppingCart_when_addToCart() {

        List<String> addedProductNames = addProductsToCart();


        goToShoppingCart();


        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));
        Assertions.assertEquals(2, cartItems.size(), "Item count in the cart should be exactly 2.");

        for (String productName : addedProductNames) {
            Assertions.assertTrue(cartItems.stream().anyMatch(item -> item.getText().contains(productName)), "The product " + productName + " is not in the cart.");
        }


        logout ();

    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() {

        addProductsToCart();

        goToShoppingCart();

        goToCheckout ();

        fillUserInfo();

        goToSummaryPage ();

        summarySubtotalLabel ();

        WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_summary_container")));
        Assertions.assertTrue(summary.isDisplayed(), "Is not displayed");


        logout ();



    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {

        addProductsToCart();

        goToShoppingCart();

        goToCheckout ();

        fillUserInfo();

        goToSummaryPage ();

        CompleteOrder ();

        List<WebElement> cartBadge = driver.findElements(By.className("shopping_cart_badge"));

        Assertions.assertTrue(cartBadge.isEmpty(), "Test failed: shopping_cart_badge is present as a child.");


        logout ();


    }
    }










