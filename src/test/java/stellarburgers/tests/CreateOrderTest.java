package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.api.models.Order;
import stellarburgers.api.models.User;
import stellarburgers.steps.OrderSteps;
import stellarburgers.steps.UserSteps;
import stellarburgers.utils.DataGenerator;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@DisplayName("Тесты создания заказа")
public class CreateOrderTest {
    private final UserSteps userSteps = new UserSteps();
    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Создание заказа с авторизацией и ингредиентами")
    public void createOrderWithAuthAndIngredientsTest() {
        User testUser = DataGenerator.getRandomUser();
        String accessToken = userSteps.createUniqueUser(testUser);
        assertNotNull("Access token не должен быть null", accessToken);

        List<String> validIngredients = orderSteps.getValidIngredients();
        Order order = new Order();
        order.setIngredients(validIngredients.subList(0, 2));

        orderSteps.createOrderWithAuth(order, accessToken);

        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWithoutAuthTest() {
        List<String> validIngredients = orderSteps.getValidIngredients();
        Order order = new Order();
        order.setIngredients(validIngredients.subList(0, 2));

        orderSteps.createOrderWithoutAuth(order);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        User testUser = DataGenerator.getRandomUser();
        String accessToken = userSteps.createUniqueUser(testUser);
        assertNotNull("Access token не должен быть null", accessToken);

        orderSteps.tryCreateOrderWithoutIngredients(accessToken);

        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    public void createOrderWithInvalidIngredientHashTest() {
        User testUser = DataGenerator.getRandomUser();
        String accessToken = userSteps.createUniqueUser(testUser);
        assertNotNull("Access token не должен быть null", accessToken);

        orderSteps.tryCreateOrderWithInvalidIngredientHash(accessToken);

        userSteps.deleteUser(accessToken);
    }
}