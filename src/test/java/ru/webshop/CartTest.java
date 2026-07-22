package ru.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.webshop.page.CartPage;
import ru.webshop.page.MainPage;
import ru.webshop.page.ProductPage;
import ru.webshop.steps.AuthSteps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.webshop.config.Config.WEB_SHOP_URL;

@Epic("Интернет-магазин")
@Feature("Корзина")
@Story("Добавление товара в корзину")
public class CartTest {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    @DisplayName("Успешное добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Rofest")
    @Link(name = "задание Allure",url = "https://github.com/Rofest/AQA")
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