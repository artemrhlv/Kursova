package ui.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewProjectElements {

    public static SelenideElement nameField() {
        return $("#form-name");
    }

    public static SelenideElement identifierField() {
        return $("#form-identifier");
    }

    public SelenideElement columnTasksLimitsCheckbox() {
        return $("#project-creation-form > label:nth-child(9) > input[type=checkbox]");
    }

    public SelenideElement taskLimitField() {
        return $("#form-task_limit");
    }

    public static SelenideElement saveButton() {
        return $("#project-creation-form > div.js-submit-buttons-rendered > div > button");
    }




}
