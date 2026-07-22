package ru.webshop.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class DesktopsPage {

    private final ElementsCollection productCards = $$("div.product-grid div");

    public ProductPage openProduct(int index) {
        productCards.get(index).click();
        return new ProductPage();
    }
}