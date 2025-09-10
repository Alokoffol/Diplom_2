package stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellarburgers.api.client.OrderClient;
import stellarburgers.api.models.Order;

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class OrderSteps {
    private final OrderClient orderClient = new OrderClient();

    @Step("Создание заказа с авторизацией")
    public void createOrderWithAuth(Order order, String accessToken) {
        Response response = orderClient.createOrder(order, accessToken);
        assertEquals("Неверный статус код при создании заказа с авторизацией",
                SC_OK, response.statusCode());

        assertTrue("Заказ не создан успешно", response.jsonPath().getBoolean("success"));
        assertNotNull("Номер заказа не должен быть null", response.jsonPath().getInt("order.number"));
    }

    @Step("Создание заказа без авторизации")
    public void createOrderWithoutAuth(Order order) {
        Response response = orderClient.createOrder(order, null);
        assertEquals("Неверный статус код при создании заказа без авторизации",
                SC_OK, response.statusCode());

        assertTrue("Заказ не создан успешно", response.jsonPath().getBoolean("success"));
        assertNotNull("Номер заказа не должен быть null", response.jsonPath().getInt("order.number"));
    }

    @Step("Попытка создания заказа без ингредиентов")
    public void tryCreateOrderWithoutIngredients(String accessToken) {
        Order emptyOrder = new Order();
        emptyOrder.setIngredients(null);

        Response response = orderClient.createOrder(emptyOrder, accessToken);
        assertEquals("Неверный статус код при создании заказа без ингредиентов",
                SC_BAD_REQUEST, response.statusCode());

        assertFalse("Заказ не должен быть создан успешно", response.jsonPath().getBoolean("success"));
        assertEquals("Неверное сообщение об ошибке", "Ingredient ids must be provided", response.jsonPath().getString("message"));
    }

    @Step("Попытка создания заказа с неверным хешем ингредиентов")
    public void tryCreateOrderWithInvalidIngredientHash(String accessToken) {
        Order invalidOrder = new Order();
        invalidOrder.setIngredients(java.util.Arrays.asList("invalid_hash_1", "invalid_hash_2"));

        Response response = orderClient.createOrder(invalidOrder, accessToken);
        assertEquals("Неверный статус код при создании заказа с неверным хешем",
                SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

    public List<String> getValidIngredients() {
        Response response = orderClient.getIngredients();
        assertEquals("Неверный статус код при получении ингредиентов",
                SC_OK, response.statusCode());

        return response.jsonPath().getList("data._id");
    }
}