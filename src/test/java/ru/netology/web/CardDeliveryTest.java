package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {
    public String generateData(int days) {
        return
                LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String dataDelivery = generateData(3);

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void shouldValidFormCardDelivery() {

        $("[data-test-id='city'] .input__control").setValue("Казань");
        $("[data-test-id='date'] .input__control").setValue(generateData(3));
        $("[data-test-id='name'] .input__control").setValue("Кузьмин Семен");
        $("[data-test-id='phone'] .input__control").setValue("+79111133030");
        $x("//label[@data-test-id='agreement']").click();
        $(".button__text").click();
        $(".notification__content")
                .should(Condition.text("Встреча успешно забронирована на " + dataDelivery),
                        Duration.ofSeconds(15));
    }

    @Test
    void shouldSelectFromDropDownListCity() {

        $("[data-test-id='city'] .input__control").setValue("Ка");
        $$(".menu-item__control").find(exactText("Калуга")).click();
        $("[data-test-id='date'] .input__control").setValue(generateData(3));
        $("[data-test-id='name'] .input__control").setValue("Кузьмин Семен");
        $("[data-test-id='phone'] .input__control").setValue("+79111133030");
        $x("//label[@data-test-id='agreement']").click();
        $(".button__text").click();
        $(".notification__content")
                .should(Condition.text("Встреча успешно забронирована на " + dataDelivery),
                        Duration.ofSeconds(15));
    }

}
