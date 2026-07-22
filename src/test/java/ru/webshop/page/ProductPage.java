package ru.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Получить название выбранного товара")
    public String getItemName() {
        return itemName.getText();
    }

    @Step("Получить цену выбранного товара")
    public String getItemPrice() {
        return itemPrice.getText();
    }

    @Step("Выбрать процессор с индексом {index}")
    public ProductPage selectProcessor(int index) {
        processorOptions.get(0).$$("li input").get(index).click();
        return this;
    }

    @Step("Установить количество товара: {quantity}")
    public ProductPage setQuantity(String quantity) {
        quantityInput.setValue(quantity);
        return this;
    }

    @Step("Добавить товар в корзину")
    public ProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить отображение уведомления об успешном добавлении товара")
    public ProductPage successNotificationShouldBeVisible() {
        successNotification.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что количество товаров в корзине равно {quantity}")
    public ProductPage cartQuantityShouldBe(String quantity) {
        cartQuantity.shouldHave(text("(" + quantity + ")"));
        return this;
    }

    @Step("Открыть корзину")
    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }
}