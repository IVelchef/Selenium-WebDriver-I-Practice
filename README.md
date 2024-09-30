I have deleted the .idea folder, and it's possible that before the first execution of the tests, 
IntelliJ IDEA will throw an error indicating that the project is set up in a different environment.
However, after running the tests, the project should configure itself automatically.


In tests, I add items to the cart by selecting products based on their
positions (indices) on the product page, 
rather than relying on their names. 
This strategy provides me with several important advantages:

1. Resilience to Changes
   By adding products based on their positions, tests become more resilient 
to changes in product names. For instance, even if the names of the 
items change, the tests will continue to function as long 
as the products in the corresponding positions remain the same. 
This is particularly useful in situations where data or content on the website is updated.

2. Easy Management
   Using indices allows me to manage test scenarios more easily, 
especially when working with a large number of products.
This approach enables me to add items to the cart in a clearer and more structured
way without worrying about the accuracy of the names, 
saving time and effort when updating the tests.



















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
