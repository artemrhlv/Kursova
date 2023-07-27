package ui.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectElements {
    public static SelenideElement createTask2() {
        return $("#dropdown > ul > li:nth-child(4) > a");
    }
    public static SelenideElement createTask1() {
        return $("#main > div.project-header > div.dropdown-component > div > a > i.fa.fa-caret-down");
    }

    public static SelenideElement dropdownTask(int id) {
        return $("[data-task-url='/task/"+id+"']>div.task-board-expanded>div.task-board-header>div>a");
    }

    public static SelenideElement dropdownCloseTask(int id) {
        return $("#dropdown [href=\"/task/" + id + "/close\"]");
    }

    public static SelenideElement dropdownCommentTask(int id) {
        return $("#dropdown [href=\"/task/" + id + "/comment/create\"]");
    }

    public static SelenideElement closeTaskConfirmButton() {
        return $("#modal-confirm-button");
    }

    public static SelenideElement dropdownCreateComment() {
        return $("[aria-label=\"New comment\"]");
    }

    public static SelenideElement dropdownTaskSelect(int id) {
        return $("[data-task-url='/task/"+id+"']>div.task-board-expanded");
    }

}
