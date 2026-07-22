package ru.webshop.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;
import io.qameta.allure.Step;

public class DesktopsPage {

    private final ElementsCollection productCards = $$("div.product-grid div");

    @Step("Открыть товар с индексом {index}")
    public ProductPage openProduct(int index) {
        productCards.get(index).click();
        return new ProductPage();
    }
}