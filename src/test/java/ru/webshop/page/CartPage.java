package ru.webshop.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement productSubtotal = $("span.product-subtotal");

    public CartPage productNameShouldBe(String expectedName) {
        productName.shouldHave(text(expectedName));
        return this;
    }

    public CartPage productQuantityShouldBe(String expectedQuantity) {
        quantityInput.shouldHave(value(expectedQuantity));
        return this;
    }

    public CartPage productSubtotalShouldBe(String itemPrice, String itemQuantity) {
        float expectedSubtotal = Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity);
        productSubtotal.shouldHave(text(String.valueOf(expectedSubtotal)));
        return this;
    }
}