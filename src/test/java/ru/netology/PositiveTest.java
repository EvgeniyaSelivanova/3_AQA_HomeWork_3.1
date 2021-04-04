package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PositiveTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestSomething() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Василий");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText(" Ваша заявка успешно отправлена!," +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestTwoWordsInName() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText(" Ваша заявка успешно отправлена!," +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestTwoWordsWithDashInSurname() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[data-test-id='name'] input").setValue("Иванов-Петров Василий");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText(" Ваша заявка успешно отправлена!," +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestSmallAndBigWordsInName() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
//        султан Занзибара в 1890—1893 годах
        form.$("[data-test-id='name'] input").setValue("Али ибн Саид");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText(" Ваша заявка успешно отправлена!," +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFourSmallAndBigWordsWithDashInName() {
        SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
//        Султан Египта и Хиджаза, мамлюк абхазского происхождения.
        form.$("[data-test-id='name'] input").setValue("Али-бей аль-Кабир");
        form.$("[data-test-id='phone'] input").setValue("+79990001122");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText(" Ваша заявка успешно отправлена!," +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

}
