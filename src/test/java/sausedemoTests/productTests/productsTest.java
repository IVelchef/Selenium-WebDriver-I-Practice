package sausedemoTests.productTests;

import base.authenticated;
import enums.BrowserTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class productsTest extends authenticated {


    @BeforeAll
    public static void setup(){

        authenticateWithUser("standard_user", "secret_sauce");
    }

    @Test
    public void addProduct_by_name(){
//        var product = getProductByTitle("Sauce Labs Backpack");
//        product.findElement(By.className("btn_inventory")).click();
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








