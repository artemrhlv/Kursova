package api.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.models.args.BodyArgs;

import static utils.EnvProperties.API_URL;

//винесений окремо пост метод, який потім можна перевикористовувати
public class BaseApiSteps {
    public Response postRequest(String username, String token, BodyArgs bodyArgs) {
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .post(API_URL);
    }

    public Response putRequest(String username, String token, BodyArgs bodyArgs) {
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .put(API_URL);
    }

    public Response patchRequest(String username, String token, BodyArgs bodyArgs) {
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .patch(API_URL);
    }

    public Response getRequest(String username, String token, BodyArgs bodyArgs) {
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .get(API_URL);
    }

    public Response getAllUsers2(String username, String token, BodyArgs bodyArgs) {
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .get(API_URL);
    }

}