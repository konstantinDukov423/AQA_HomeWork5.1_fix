package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.awt.*;
import java.security.Key;

import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var deleteText = Keys.chord(Keys.CONTROL + "A", Keys.BACK_SPACE);
        var validCity = DataGenerator.generateCity("ru");
        var validName = DataGenerator.generateName("ru");
        var validPhone = DataGenerator.generatePhone("ru");
        var firstMeetingDate = DataGenerator.generateDate(4);
        var secondMeetingDate = DataGenerator.generateDate(7);
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(deleteText);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $x("//span[@class='checkbox__box']").click();
        $x("//span[@class='button__text']").click();
        $("[data-test-id='date'] input").sendKeys(deleteText);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $x("//span[@class='button__text']").click();
        $x("//div[@class='notification__content']//span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//div[@class='notification__content']").shouldBe(Condition.appear);
    }
}
