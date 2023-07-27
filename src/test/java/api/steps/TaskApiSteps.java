package api.steps;

import api.models.TaskDetails;
import api.models.args.projects.CreateProject;
import api.models.args.projects.AddProjectUser;
import api.models.args.tasks.CreateTaskRequest;
import api.models.args.tasks.ResponseTask;
import api.models.args.users.CreateUser;
import api.models.args.tasks.CreateTask;
import api.models.args.users.ResponseUser;
import api.models.args.users.UserId;
import api.models.args.tasks.TaskId;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import api.models.BoardInfo;
import api.models.Result;
import api.models.args.BodyArgs;
import api.models.args.projects.ProjectId;

import java.util.List;

import static api.methods.Tasks.CREATE_TASK;
import static api.methods.Tasks.DELETE_TASK;
import static api.methods.Tasks.GET_ALL_TASKS;
import static api.methods.Tasks.GET_TASK;
import static api.methods.Users.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;


public class TaskApiSteps extends BaseApiSteps {

    public Result<List<TaskDetails>> getTask(Integer taskId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectId(taskId))
                .method(GET_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<Result<List<TaskDetails>>>() {
        });
    }

    public String createTaskInProject(String title, int project_id) {
        CreateTask args = CreateTask.builder()
                .title(title)
                .project_id(project_id)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(CREATE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs); //пост-запит
        response.then().statusCode(200);
        Result result = response.as(Result.class);  //діставання результату
        return result.getResult().toString();
    }

    public boolean deleteTask(String taskId) {  //видалення коричтувача


        BodyArgs bodyArgs = BodyArgs.builder().
                params(new TaskId(Integer.valueOf(taskId)))  //в параметрах ІД користувача (обєкт, який містить тільки юзер ІД)
                .method(DELETE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(Result.class).getResult();
    }

    public Result<List<ResponseTask>> getAllTasks(int project_id, int status) {
        CreateTaskRequest args = CreateTaskRequest.builder()
                .project_id(project_id)
                .status_id(status)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(GET_ALL_TASKS)
                .build();

        Response response = getRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<Result<List<ResponseTask>>>() {
        });
    }
}
