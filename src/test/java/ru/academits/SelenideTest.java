package ru.academits;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @Test
    public void openPageWithSelenideTest() {
        Configuration.startMaximized = true;
        open("https://the-internet.herokuapp.com/");
    }
}
