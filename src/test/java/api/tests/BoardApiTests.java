package api.tests;

import api.models.args.projects.AllProjectsResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.models.BoardInfo;
import api.models.Result;
import api.steps.BoardApiSteps;

import java.util.List;
public class BoardApiTests {

    private static final String PROJECT_NAME = "Test_API_Project087";
    private static final String PROJECT_DESCRIPTION = "interesting";
    private static final String IDENTIFIER = "QWER";
    BoardApiSteps boardApiSteps = new BoardApiSteps();
    private String project_id;

    @Test
    public void checkBoardApi() {
        project_id = boardApiSteps.createBoardForProject(PROJECT_NAME, PROJECT_DESCRIPTION, IDENTIFIER);
        System.out.println(project_id);

        BoardApiSteps boardApiSteps = new BoardApiSteps();
        Result<List<BoardInfo>> boardInfoResult = boardApiSteps.getBoardForProject(Integer.valueOf(project_id));
        Assert.assertTrue(boardInfoResult.getResult().size() > 0, "Board not created");
        System.out.println(boardInfoResult.getResult().get(0).getName());
        boardApiSteps.deleteProject(project_id);

        Result<List<AllProjectsResponse>> boardInfoResult2 = boardApiSteps.getAllProjects();
        Assert.assertFalse(boardInfoResult2.getResult().size() > 0, "Board not deleted");
    }
}