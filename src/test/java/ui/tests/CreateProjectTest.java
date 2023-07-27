package ui.tests;

import api.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.elements.MainElements;
import ui.elements.CreateNewProjectElements;
import ui.steps.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class CreateProjectTest extends BaseTest {

    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;
    private static final String USERNAME = "user9093";
    private static final String PASSWORD = "myTestPassword";
    private static final String PROJECT_NAME = "test_project023";
    private static final String PROJECT_IDENTIFIER = "TESTPROJECT";

    @BeforeClass
    @Step("Creating new user")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD); //створення нового юзера
        System.out.println(userId);
    }

    @Test
    public void createNewProject() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .assertMainSectionIsOpened();

        MainElements.dropdownProjectMenu().click();
        MainElements.dropdownCreateProject().click();
        CreateNewProjectElements.nameField().sendKeys(PROJECT_NAME);
        CreateNewProjectElements.identifierField().sendKeys(PROJECT_IDENTIFIER);
        CreateNewProjectElements.saveButton().click();
        MainElements.mainButton().click();
        $("#dashboard > div.sidebar-content > div.table-list").shouldHave(text(PROJECT_NAME));
    }

    @AfterClass(alwaysRun = true)
    @Step("Deleting user")
    public void removeUserAfterTest() {
        userApiSteps.deleteUser(userId);
    } //видалення юзера
}
