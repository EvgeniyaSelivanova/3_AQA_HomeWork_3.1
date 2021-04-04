package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NegativeTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestEnglishWordsInName() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Ivanov Vasiliy");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='name'].input_invalid .input__sub").should(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestEmptyName() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='name'].input_invalid .input__sub").should(exactText("Поле обязательно" +
                " для заполнения")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestEmptyPhone() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").should(exactText("Поле обязательно" +
                " для заполнения")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestInvalidPhoneNumber() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='phone'] input").setValue("79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").should(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestWithoutCheckbox() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[type='button']").click();
        $("[data-test-id='agreement'].input_invalid").should(visible);
    }

}
