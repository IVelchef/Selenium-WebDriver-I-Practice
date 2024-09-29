package sausedemoTests.productTests;
import base.authenticated;
import enums.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({
            "Sauce Labs Backpack, btn_inventory , remove-sauce-labs-backpack",
            "Sauce Labs Onesie, btn_inventory, remove-sauce-labs-onesie"

    })
    public void addProduct_by_name(String productTitle, String path, String removeButtonId) {

        var product = getProductByTitle(productTitle);

        product.findElement(By.className(path)).click();

        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(removeButtonId)));

        Assertions.assertTrue(removeButton.isDisplayed(), "Remove button is not visible.");


        driver.findElement(By.id("shopping_cart_container")).click();

        WebElement cartBadge = driver.findElement(By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']"));
        int itemCount = Integer.parseInt(cartBadge.getText());

        List<WebElement> cartItem = driver.findElements(By.className("cart_item"));


        Assertions.assertTrue(itemCount >= 1, "Item count in the cart should be exactly 2, but it was: " + itemCount);

        Assertions.assertTrue(cartItem.stream().anyMatch(item -> item.getText().contains(productTitle)),
                "The product " + productTitle + " is not in the cart."
        );


    }

    @Test
    public void findAllProducts() {

        var productList = getAllProducts();


        String firstProductName = productList.get(0).findElement(By.className("inventory_item_name")).getText();
        String fifthProductName = productList.get(4).findElement(By.className("inventory_item_name")).getText();


        addProductsToCart();


        driver.findElement(By.id("shopping_cart_container")).click();
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));



        Assertions.assertEquals(2, cartItems.size(), "Item count in the cart should be exactly 2.");

        List<String> expectedProducts = List.of(firstProductName, fifthProductName);

        for (WebElement item : cartItems) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();
            Assertions.assertTrue(expectedProducts.contains(itemName), "Unexpected product in the cart: " + itemName);
        }
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart() {

        List<String> addedProductNames = addProductsToCart();


        driver.findElement(By.id("shopping_cart_container")).click();
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));



        Assertions.assertEquals(2, cartItems.size(), "Item count in the cart should be exactly 2.");


        for (String productName : addedProductNames) {
            Assertions.assertTrue(cartItems.stream().anyMatch(item -> item.getText().contains(productName)), "The product " + productName + " is not in the cart.");
        }

    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() {

        addProductsToCart();

        fillUserInfo();

        driver.findElement(By.id("continue")).click();



        VerifySummaryPage();


    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {

        addProductsToCart();

        fillUserInfo();

        driver.findElement(By.id("continue")).click();


        VerifySummaryPage();


        WebElement finish = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        finish.click();

        List<WebElement> cartBadge = driver.findElements(By.className("shopping_cart_badge"));

        Assertions.assertTrue(cartBadge.isEmpty(), "Test failed: shopping_cart_badge is present as a child.");


    }
    }










