package api.tests;

import api.models.args.tasks.ResponseTask;
import api.models.args.users.ResponseUser;
import api.steps.TaskApiSteps;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.models.BoardInfo;
import api.models.Result;
import api.steps.BoardApiSteps;

import java.util.List;
public class TaskApiTests {

    private static final String PROJECT_NAME = "Test_API_Project087";
    private static final String PROJECT_DESCRIPTION = "interesting";
    private static final String IDENTIFIER = "ROFL";
    private static final String TASK_TITLE = "Taskname4421";
    BoardApiSteps boardApiSteps = new BoardApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    private String project_id;
    private String task_id;

    @Test
    public void checkTaskApi() {
        project_id = boardApiSteps.createBoardForProject(PROJECT_NAME, PROJECT_DESCRIPTION, IDENTIFIER);
        System.out.println(project_id);

        task_id = taskApiSteps.createTaskInProject(TASK_TITLE, Integer.parseInt(project_id));
        System.out.println(task_id);

        Result<List<ResponseTask>> allTasksInfoResult = taskApiSteps.getAllTasks(Integer.parseInt(project_id), 1);
        Assert.assertEquals(allTasksInfoResult.getResult().get(0).getTitle(), TASK_TITLE);

        taskApiSteps.deleteTask(task_id);
        boardApiSteps.deleteProject(project_id);

        Result<List<ResponseTask>> allTasksInfoResult2 = taskApiSteps.getAllTasks(Integer.valueOf(project_id), 1);
        Assert.assertFalse(allTasksInfoResult2.getResult().size() > 0, "There are no tasks deleted");
    }

}
