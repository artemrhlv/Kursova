package api.steps;

import api.models.args.users.ResponseUser;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import api.models.Result;
import api.models.args.BodyArgs;
import api.models.args.users.CreateUser;
import api.models.args.users.UserId;

import java.util.List;

import static api.enums.UserRoles.USER;
import static api.methods.Users.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps { //препосткондішени для створення/видалення юзера
    public String createUser(String username, String pass) {
        CreateUser args = CreateUser.builder()  //(створення жсон класу) формування тіла для передачі параметрів
                .username(username)
                .name(username)
                .password(pass)
                .email(username + "@mail.com")
                .role(USER.getRole())
                .build(); //передається в клас BodyArgs

        BodyArgs bodyArgs = BodyArgs.builder().
                params(args) //передається в клас BodyArgs яку параметри (в данному випадку args)
                .method(CREATE_USER) //передається в клас BodyArgs який метод (в данному випадку створення користувача)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs); //пост-запит
        response.then().statusCode(200);
        Result result = response.as(Result.class);  //діставання результату
        return result.getResult().toString();  //повернеться тільки ІД нового користувача, тому метод повертає стрінг, окремо створювати метот тут не потрібо
    }

    public boolean deleteUser(String userId) {  //видалення коричтувача

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new UserId(Integer.valueOf(userId)))  //в параметрах ІД користувача (обєкт, який містить тільки юзер ІД)
                .method(DELETE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(Result.class).getResult();
    }

    public ResponseUser getUser(String userId) {
        BodyArgs bodyArgs = BodyArgs.builder().
                params(new UserId(Integer.valueOf(userId))) //передається в клас BodyArgs яку параметри (в данному випадку args)
                .method(GET_USER) //передається в клас BodyArgs який метод (в данному випадку створення користувача)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(ResponseUser.class);
    }


    public Result<List<ResponseUser>> getUserForProject(Integer userId) {

        BodyArgs bodyArgs = BodyArgs.builder().
                params(new UserId(userId))
                .method(GET_USER)
                .build();

        Response response = getRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<Result<List<ResponseUser>>>() {
        });  //структура для рест.ашуред що буде викоривтовуватись дженерік з настумними можливими методами, які типи в респонсі ми очікуєм (різні борди)
    }

    public Result<List<ResponseUser>> getAllUsers() {

        BodyArgs bodyArgs = BodyArgs.builder()
                .method(GET_ALL_USERS)
                .build();

        Response response = getRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return response.as(new TypeRef<Result<List<ResponseUser>>>() {
        });
    }
}

