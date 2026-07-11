package ru.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.webshop.page.MainPage;
import ru.webshop.page.ProductPage;
import ru.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.open;
import static ru.webshop.config.Config.WEB_SHOP_URL;

public class CartTest {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        String itemQuantity = "2";
        int processorIndex = 0;

        ProductPage productPage = open(WEB_SHOP_URL, MainPage.class)
                .openDesktopsPage()
                .openFirstProduct()
                .selectProcessor(processorIndex);

        String itemName = productPage.getItemName();
        String itemPrice = productPage.getItemPrice();

        productPage
                .setQuantity(itemQuantity)
                .addToCart()
                .cartQuantityShouldBe(itemQuantity)
                .openCart()
                .productNameShouldBe(itemName)
                .productQuantityShouldBe(itemQuantity)
                .productSubtotalShouldBe(itemPrice, itemQuantity);
    }
}