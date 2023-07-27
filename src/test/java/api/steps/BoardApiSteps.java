package api.steps;

import api.models.args.projects.AllProjectsResponse;
import api.models.args.projects.CreateProject;
import api.models.args.projects.AddProjectUser;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import api.models.BoardInfo;
import api.models.Result;
import api.models.args.BodyArgs;
import api.models.args.projects.ProjectId;

import java.util.List;

import static api.methods.Boards.GET_BOARD;
import static api.methods.Users.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;
import static api.methods.Boards.CREATE_PROJECT;
import static api.methods.Boards.GET_ALL_PROJECTS;
import static api.methods.Boards.ADD_PROJECT_USER;
import static api.methods.Boards.DELETE_PROJECT;


public class BoardApiSteps extends BaseApiSteps {
    public Result<List<BoardInfo>> getBoardForProject(Integer projectId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectId(projectId))
                .method(GET_BOARD)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<Result<List<BoardInfo>>>() {
        });
    }

    public String createBoardForProject(String name, String description, String identifier) {
        CreateProject args = CreateProject.builder()
                .name(name)
                .description(description)
                .identifier(identifier)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(CREATE_PROJECT)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs); //пост-запит
        response.then().statusCode(200);
        Result result = response.as(Result.class);  //діставання результату
        return result.getResult().toString();
    }

    public Boolean addProjectUser(int project_id, int user_id, String role) {
        AddProjectUser args = AddProjectUser.builder()
                .project_id(project_id)
                .user_id(user_id)
                .role(role)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args)
                .method(ADD_PROJECT_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result result = response.as(Result.class);
        boolean responseToBoolean = Boolean.parseBoolean(result.getResult().toString());
        return responseToBoolean;
    }

    public boolean deleteProject(String boardId) {  //видалення коричтувача

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new ProjectId(Integer.valueOf(boardId)))  //в параметрах ІД користувача (обєкт, який містить тільки юзер ІД)
                .method(DELETE_PROJECT)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(Result.class).getResult();
    }

    public Result<List<AllProjectsResponse>> getAllProjects() {

        BodyArgs bodyArgs = BodyArgs.builder()
                .method(GET_ALL_PROJECTS)
                .build();

        Response response = getRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<Result<List<AllProjectsResponse>>>() {
        });
    }


}