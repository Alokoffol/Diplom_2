package stellarburgers.api.client;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import stellarburgers.api.endpoints.Endpoints;
import stellarburgers.api.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Endpoints.BASE_URL)
                .body(user)
                .when()
                .post(Endpoints.CREATE_USER);
    }

    public Response loginUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Endpoints.BASE_URL)
                .body(user)
                .when()
                .post(Endpoints.LOGIN);
    }

    public Response deleteUser(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(Endpoints.BASE_URL)
                .when()
                .delete(Endpoints.USER_DATA);
    }
}