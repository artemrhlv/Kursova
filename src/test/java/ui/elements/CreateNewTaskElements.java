package ui.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewTaskElements {

    public static SelenideElement titleField() {
        return $("#form-title");
    }

    public static SelenideElement markdownField() {
        return $("textarea[aria-label=\"Description\"]");
    }

    public static SelenideElement tagsField() {
        return $("ul[class=\"select2-selection__rendered\"]");
    }

    public static SelenideElement saveButton() {
        return $("button[type=\"submit\"]");
    }


}
