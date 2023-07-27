package ui.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ui.elements.MainElements;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends MainElements {

    public MainPage openMainPage() {
        open("http://127.0.0.1/dashboard");
        return new MainPage();
    }

    public MainPage assertMainSectionIsOpened() {
        mainSection().shouldBe(Condition.visible);
        return this;
    }

    public MainPage assertFalseLogIn() {
        falseSelection().shouldBe(Condition.visible);
        return this;
    }

}
