package ui.tests;

import io.qameta.allure.Step;
import org.testng.annotations.*;
import api.steps.UserApiSteps;
import ui.steps.LoginPage;


public class LoginTests extends BaseTest {
    private static final String USERNAME = "user8899";
    private static final String FALSE_USERNAME_1 = "qqe5gwe654gh6wh584we6h";
    private static final String FALSE_USERNAME_2 = "null";
    private static final String PASSWORD = "myTestPassword";
    private static final String FALSE_PASSWORD_1 = "qeg46q5e8gh4qw6h54h651r";
    private static final String FALSE_PASSWORD_2 = "null";

    UserApiSteps userApiSteps = new UserApiSteps();  //використовуємо шаблон для апі тестів
    private String userId;

    @BeforeClass
    @Step("Creating new user")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD); //створення нового юзера
        System.out.println(userId);
    }

    @Test
    @Step("Negative test 1")
    public void loginByFalseUser1() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(FALSE_USERNAME_1, FALSE_PASSWORD_1)
                .assertFalseLogIn();
    }

    @Test
    @Step("Negative test 2")
    public void loginByFalseUser2() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(FALSE_USERNAME_2, FALSE_PASSWORD_2)
                .assertFalseLogIn();
    }

    @Test
    @Step("Positive login test")
    public void loginByNewUser() {
        new LoginPage()
                .openLoginPage()   //відкриваємо логін сторінку
                .loginByUser(USERNAME, PASSWORD)  //логінимся
                .assertMainSectionIsOpened();  //перевірка чи знаходимося на головній сторінці
    }

    @AfterClass(alwaysRun = true)
    @Step("Deleting user")
    public void removeUserAfterTest() {
        userApiSteps.deleteUser(userId);
    } //видалення юзера
}
