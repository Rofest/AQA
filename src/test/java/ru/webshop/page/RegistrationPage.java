package ru.webshop.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import io.qameta.allure.Step;
public class RegistrationPage {

    private final SelenideElement genderMaleRadio = $("#gender-male");
    private final SelenideElement firstNameInput = $("#FirstName");
    private final SelenideElement lastNameInput = $("#LastName");
    private final SelenideElement emailInput = $("#Email");
    private final SelenideElement passwordInput = $("#Password");
    private final SelenideElement confirmPasswordInput = $("#ConfirmPassword");
    private final SelenideElement registerButton = $("#register-button");
    private final SelenideElement resultMessage = $(".result");


    @Step("Зарегистрировать пользователя: "+ "{firstName} {lastName}, email: {email}")
    public RegistrationPage register(String firstName, String lastName, String email, String password) {
        genderMaleRadio.click();
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        emailInput.setValue(email);
        passwordInput.setValue(password);
        confirmPasswordInput.setValue(password);
        registerButton.click();

        resultMessage.shouldHave(text("Your registration completed"));

        return this;
    }
}