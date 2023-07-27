package api.tests;

import api.models.Result;
import api.models.args.users.ResponseUser;
import org.testng.Assert;
import org.testng.annotations.*;
import api.steps.UserApiSteps;

import java.util.List;

public class UserApiTests {
    private static final String USERNAME = "user8899";
    private static final String PASSWORD = "myTestPassword";

    private String userId;
    UserApiSteps userApiSteps = new UserApiSteps();
    @Test
    public void checkUserApi() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        Result<List<ResponseUser>> allUsersInfoResult = userApiSteps.getAllUsers();
        Assert.assertEquals(allUsersInfoResult.getResult().get(1).getName(), USERNAME);

        userApiSteps.deleteUser(userId);
        Result<List<ResponseUser>> allUsersInfoResult2 = userApiSteps.getAllUsers();
        Assert.assertTrue(allUsersInfoResult2.getResult().size() > 0, "There are no users created");
    }
}
