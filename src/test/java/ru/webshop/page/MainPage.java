package ru.webshop.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final ElementsCollection topMenuLinks = $$("ul.top-menu li a");

    public DesktopsPage openDesktopsPage() {
        topMenuLinks.get(1).hover();
        $(byText("Desktops")).click();
        return new DesktopsPage();
    }
}