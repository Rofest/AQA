package ru.webshop.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement productSubtotal = $("span.product-subtotal");


    @Step("Получить название товара в корзине")
    public String getProductName() {
        return productName.getText();
    }
    @Step("Получить количество товара в корзине")
    public String getQuantity() {
        return quantityInput.getValue();
    }
    @Step("Получить итоговую стоимость товара в корзине")
    public String getSubtotal() {
        return productSubtotal.getText();
    }
}