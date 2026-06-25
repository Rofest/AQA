package ru.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

  private final SelenideElement firstNameInput = $("#firstName"),
      lastNameInput = $("#lastName"),
      emailInput = $("#userEmail"),
      phoneInput = $("#userNumber"),
      genderWrapper = $("#genterWrapper"),
      hobbiesWrapper = $("#hobbiesWrapper"),
      subjectsInput = $("#subjectsInput"),
      addressInput = $("#currentAddress"),
      stateSelect = $("#state"),
      citySelect = $("#city"),
      submitButton = $("#submit"),
      modalContent = $(".modal-content");

    public PracticeFormPage closeAds() {
      executeJavaScript(
          "document.querySelectorAll('iframe, footer, #fixedban, #RightSide_Advertisement')" +
              ".forEach(element => element.remove())"
      );

      return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public PracticeFormPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public PracticeFormPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public PracticeFormPage selectHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).click();
        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public PracticeFormPage selectState(String state) {
        stateSelect.scrollIntoView(true).click();
        $(byText(state)).click();
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        citySelect.click();
        $(byText(city)).click();
        return this;
    }

    public PracticeFormPage submit() {
        submitButton.scrollIntoView(true).click();
        return this;
    }

    public PracticeFormPage verifyCorrectInfo(String... values) {
        for (String value : values) {
            modalContent.shouldHave(text(value));
        }
        return this;
    }
}