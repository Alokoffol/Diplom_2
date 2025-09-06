package stellarburgers.api.client;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import stellarburgers.api.endpoints.Endpoints;
import stellarburgers.api.models.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    public Response createOrder(Order order, String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken != null ? accessToken : "")
                .baseUri(Endpoints.BASE_URL)
                .body(order)
                .when()
                .post(Endpoints.CREATE_ORDER);
    }

    public Response getIngredients() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Endpoints.BASE_URL)
                .when()
                .get(Endpoints.GET_INGREDIENTS);
    }
}