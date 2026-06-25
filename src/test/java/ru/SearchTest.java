package ru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.pages.PracticeFormPage;
import ru.pages.YandexSearchPage;

import static com.codeborne.selenide.Selenide.open;

public class SearchTest {

    private static final String PRACTICE_FORM_URL = "https://demoqa.com/automation-practice-form";

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void mentoringPriceTest() {
        /*
        Проверить, что предоплата по обучению - 47000 рублей
         */
        new YandexSearchPage()
                .openPage()
                .search("bulgakov qa")
                .submit()
                .closePopup()
                .openSite("ivanbulgakovqa.ru")
                .goToPricing()
                .clickBuyButton()
                .priceShouldBe("47 000.00");
    }

    @Test
    void studentRegistrationFormTest() {

      String firstName = "Nikita";
      String lastName = "QA";
      String email = "nikita@test.com";
      String number = "1234567890";
      String male = "Male";
      String sports = "Sports";
      String subject = "English";
      String address = "Test address";
      String state = "NCR";
      String city = "Delhi";
      open(PRACTICE_FORM_URL, PracticeFormPage.class)
                .closeAds()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhone(number)
                .selectGender(male)
                .selectHobby(sports)
                .setSubject(subject)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .submit()
                .verifyCorrectInfo(
                    "Thanks for submitting the form",
                    firstName + " " + lastName,
                    email,
                    male,
                    number,
                    sports,
                    subject,
                    address,
                    state + " " + city
                );
    }
}