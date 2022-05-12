package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
            void setUp() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.BACK_SPACE);
    }
    @Test
    void shouldValidFormCardDelivery() {


        $("[data-test-id='city'] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").setValue(dateDelivery);
        $("[data-test-id='name'] .input__control").setValue("Кузьмин Семен");
        $("[data-test-id='phone'] .input__control").setValue("+79111133030");
        $x("//label[@data-test-id='agreement']").click();
        $(".button__text").click();
        $("[data-test-id='notification'] .notification__title")
                .should(visible, Duration.ofSeconds(15));
    }


    @Test
    void shouldSelectFromDropDownListCity() {

        $("[data-test-id='city'] .input__control").setValue("Ка");
        $x("//*[text()='Казань']").click();
        $("[data-test-id='date'] .input__control").setValue(dateDelivery);
        $("[data-test-id='name'] .input__control").setValue("Кузьмин Семен");
        $("[data-test-id='phone'] .input__control").setValue("+79111133030");
        $x("//label[@data-test-id='agreement']").click();
        $(".button__text").click();
        $("[data-test-id='notification'] .notification__title")
                .should(visible, Duration.ofSeconds(15));
    }

}
