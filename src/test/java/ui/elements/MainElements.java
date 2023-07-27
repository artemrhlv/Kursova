package ui.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainElements {
    public SelenideElement mainSection() {
        return $("#main");
    }

    public SelenideElement falseSelection() {
        return $x("/html/body/div/p");
    }

    public static SelenideElement dropdownProjectMenu() {
        return $("body > header > div.menus-container > div.dropdown.header-creation-menu > a");
    }

    public static SelenideElement dropdownCreateProject() {
        return $("#dropdown > ul > li > a");
    }

    public static SelenideElement mainButton() {
        return $("body > header > div.title-container > h1 > span.logo > a");
    }

    public static SelenideElement selectProject() {
        return $("#dashboard > div.sidebar-content > div.table-list > div.table-list-row.table-border-left > div:nth-child(1) > span > a");
    }
}