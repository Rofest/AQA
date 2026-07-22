package ru.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {

    private final SelenideElement itemName = $("[itemprop=name]");
    private final SelenideElement itemPrice = $("[itemprop=price]");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $("div.bar-notification.success");
    private final SelenideElement cartQuantity = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");
    private final ElementsCollection processorOptions = $$("dl dd ul li");

    public String getItemName() {
        return itemName.getText();
    }

    public String getItemPrice() {
        return itemPrice.getText();
    }

    public ProductPage selectProcessor(int index) {
        processorOptions.get(0).$$("li input").get(index).click();
        return this;
    }


    public ProductPage setQuantity(String quantity) {
        quantityInput.setValue(quantity);
        return this;
    }

    public ProductPage addToCart() {
        addToCartButton.click();
        return this;
    }
    public ProductPage successNotificationShouldBeVisible() {
        successNotification.shouldBe(visible);
        return this;
    }


    public ProductPage cartQuantityShouldBe(String quantity) {
        cartQuantity.shouldHave(text("(" + quantity + ")"));
        return this;
    }

    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }
}