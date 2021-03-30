package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NegativeTest {

    @Test
    void shouldTestEnglishWordsInName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Ivanov Vasiliy");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white'] [class='input__sub']").should(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_invalid input_theme_alfa-on-white'] [class='input__sub']").should(exactText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestEmptyPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[class='input input_type_tel input_view_default input_size_m input_width_available input_has-label input_invalid input_theme_alfa-on-white'] [class='input__sub']").should(exactText("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    @Test
    void shouldTestInvalidPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='phone'] input").setValue("79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[class='input input_type_tel input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white'] [class='input__sub']").should(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestWithoutCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[type='button']").click();
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white input_invalid']").should(exist);
    }

}
