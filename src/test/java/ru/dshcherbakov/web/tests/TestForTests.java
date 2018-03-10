package ru.dshcherbakov.web.tests;

import org.junit.jupiter.api.Test;
import ru.dshcherbakov.web.environment.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TestForTests extends TestBase {

    @Test
    void test(){
        $("#lst-ib").setValue("cars").pressEnter();
        $("#rso").shouldHave(text("cars"));
    }
}
