
package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = ru.netology.delivery.data.DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = ru.netology.delivery.data.DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = ru.netology.delivery.data.DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id ='date'] input").sendKeys(Keys.SHIFT, Keys.HOME); // выделение текста кнопками Shift+Home
        $("[data-test-id ='date'] input").sendKeys(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();

        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15)) //ассерт проверки видимости
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate)) //ассерт проверки текста
        ;

        $("[data-test-id ='date'] input").sendKeys(Keys.SHIFT, Keys.HOME); // выделение текста кнопками Shift+Home
        $("[data-test-id ='date'] input").sendKeys(secondMeetingDate);
        $(".button").click();

        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15)) //ассерт проверки видимости
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")) //ассерт проверки текста
        ;

        $("[data-test-id='replan-notification'] .button").click();

        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15)) //ассерт проверки видимости
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate)) //ассерт проверки текста
        ;

    }
}