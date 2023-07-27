package ui.tests;

import api.steps.UserApiSteps;
import api.steps.BoardApiSteps;
import api.steps.TaskApiSteps;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import ui.elements.CreateNewProjectElements;
import ui.elements.CreateNewTaskElements;
import ui.elements.MainElements;
import ui.elements.ProjectElements;
import ui.steps.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CommentTaskTest  extends BaseTest{

    private static final String USERNAME = "user8899";
    private static final String PASSWORD = "myTestPassword";
    private static final String PROJECT_NAME = "Test_API_Project087";
    private static final String PROJECT_DESCRIPTION = "interesting";
    private static final String IDENTIFIER = "ROFL";
    private static final String TASK_TITLE = "Taskname4421";
    private static final String COMMENT = "Testing some comments";

    UserApiSteps userApiSteps = new UserApiSteps();
    BoardApiSteps boardApiSteps = new BoardApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    private String userId;
    private String project_id;
    private String task_id;
    private Boolean isProjectAddedToUser;
    private String role = "project-member";

    @BeforeClass
    @Step("Creating new user")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        System.out.println(userId);

        project_id = boardApiSteps.createBoardForProject(PROJECT_NAME, PROJECT_DESCRIPTION, IDENTIFIER);
        System.out.println(project_id);

        isProjectAddedToUser = boardApiSteps.addProjectUser(Integer.parseInt(project_id), Integer.parseInt(userId), role);
        System.out.println(isProjectAddedToUser);

        isProjectAddedToUser = boardApiSteps.addProjectUser(Integer.parseInt(project_id), 1, role);
        System.out.println(isProjectAddedToUser);

        task_id = taskApiSteps.createTaskInProject(TASK_TITLE, Integer.parseInt(project_id));
        System.out.println(task_id);
    }

    @Test
    public void createNewTask() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .assertMainSectionIsOpened();
        MainElements.selectProject().click();
        ProjectElements.dropdownTask(Integer.parseInt(task_id)).click();
        ProjectElements.dropdownCommentTask(Integer.parseInt(task_id)).click();
        ProjectElements.dropdownCreateComment().sendKeys(COMMENT);
        CreateNewTaskElements.saveButton().click();
        ProjectElements.dropdownTaskSelect(Integer.parseInt(task_id)).click();
        $("#task-view").shouldHave(text(COMMENT));
    }

    @AfterClass(alwaysRun = true)
    @Step("Deleting user")
    public void removeDataAfterTest() {
        taskApiSteps.deleteTask(task_id);
        boardApiSteps.deleteProject(project_id);
        userApiSteps.deleteUser(userId);
    }
}
