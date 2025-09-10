package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
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
    private User testUser;
    private String accessToken;

    @Before
    public void setUp() {
        testUser = DataGenerator.getRandomUser();
        accessToken = userSteps.createUniqueUser(testUser);
        assertNotNull("Access token не должен быть null", accessToken);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и ингредиентами")
    @Description("Проверка успешного создания заказа с авторизацией и валидными ингредиентами")
    public void createOrderWithAuthAndIngredientsTest() {
        List<String> validIngredients = orderSteps.getValidIngredients();
        Order order = new Order();
        order.setIngredients(validIngredients.subList(0, 2));

        orderSteps.createOrderWithAuth(order, accessToken);
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Проверка создания заказа без авторизации с валидными ингредиентами")
    public void createOrderWithoutAuthTest() {
        List<String> validIngredients = orderSteps.getValidIngredients();
        Order order = new Order();
        order.setIngredients(validIngredients.subList(0, 2));

        orderSteps.createOrderWithoutAuth(order);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Проверка попытки создания заказа без указания ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        orderSteps.tryCreateOrderWithoutIngredients(accessToken);
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    @Description("Проверка попытки создания заказа с невалидными хешами ингредиентов")
    public void createOrderWithInvalidIngredientHashTest() {
        orderSteps.tryCreateOrderWithInvalidIngredientHash(accessToken);
    }
}