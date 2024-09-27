Automate the following scenarios in sausedemo application:
---------------------------------------------

1

productAddedToShoppingCart_when_addToCart()

Login

Add 2 products to shopping cart

Go to shopping Cart

Assert correct items added

---------------------------------------------

2

userDetailsAdded_when_checkoutWithValidInformation()

Login

Add 2 products to shopping cart

Go to shopping Cart

Go to Checkout

Fill User Info

Go to summary Page

Verify Summary Page

---------------------------------------------

3

orderCompleted_when_addProduct_and_checkout_withConfirm()

Login

Add 2 products to shopping cart

Go to shopping Cart

Go to Checkout

Fill User Info

Go to summary Page

Complete Order

Verify Items are removed after from Shopping Cart after the order is completed
