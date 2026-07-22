package ru.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.webshop.page.CartPage;
import ru.webshop.page.MainPage;
import ru.webshop.page.ProductPage;
import ru.webshop.steps.AuthSteps;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.webshop.config.Config.WEB_SHOP_URL;

public class CartTest {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        int productIndex = 0;
        String itemQuantity = "2";
        int processorIndex = 0;

        ProductPage productPage = open(WEB_SHOP_URL, MainPage.class)
                .openDesktopsPage()
                .openProduct(productIndex)
                .selectProcessor(processorIndex);

        String itemName = productPage.getItemName();
        String itemPrice = productPage.getItemPrice();

        CartPage cartPage = productPage
                .setQuantity(itemQuantity)
                .addToCart()
                .successNotificationShouldBeVisible()
                .cartQuantityShouldBe(itemQuantity)
                .openCart();

        String expectedTotal = new BigDecimal(itemPrice)
                .multiply(new BigDecimal(itemQuantity))
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();

        assertAll(
                () -> assertEquals(itemName, cartPage.getProductName()),
                () -> assertEquals(itemQuantity, cartPage.getQuantity()),
                () -> assertEquals(expectedTotal, cartPage.getSubtotal())
        );
    }
}