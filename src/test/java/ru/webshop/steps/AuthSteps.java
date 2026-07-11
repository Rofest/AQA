package ru.webshop.steps;

import net.datafaker.Faker;
import ru.webshop.page.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;

public class AuthSteps {

    private static final Faker faker = new Faker();

    public void registerNewUser() {
        open(WEB_SHOP_REGISTRATION_URL, RegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.superhero().name() + faker.number().positive()
                );
    }
}