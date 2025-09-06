package stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellarburgers.api.client.UserClient;
import stellarburgers.api.models.User;
import stellarburgers.api.models.LoginResponse;

import static org.junit.Assert.*;

public class UserSteps {
    private final UserClient userClient = new UserClient();

    @Step("Создание уникального пользователя")
    public String createUniqueUser(User user) {
        Response response = userClient.createUser(user);
        assertEquals("Неверный статус код при создании пользователя", 200, response.statusCode());

        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertTrue("Пользователь не создан успешно", loginResponse.isSuccess());

        return loginResponse.getAccessToken();
    }

    // Остальные методы без @Step
    public void tryCreateExistingUser(User user) {
        Response response = userClient.createUser(user);
        assertEquals("Неверный статус код при создании существующего пользователя", 403, response.statusCode());
    }

    public void tryCreateUserWithoutRequiredField(User user) {
        Response response = userClient.createUser(user);
        assertEquals("Неверный статус код при создании пользователя без обязательного поля", 403, response.statusCode());
    }

    @Step("Успешный логин пользователя")
    public String loginUserSuccessfully(User user) {
        Response response = userClient.loginUser(user);
        assertEquals("Неверный статус код при логине", 200, response.statusCode());

        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertTrue("Логин не выполнен успешно", loginResponse.isSuccess());

        return loginResponse.getAccessToken();
    }

    public void tryLoginWithInvalidData(User user) {
        Response response = userClient.loginUser(user);
        assertEquals("Неверный статус код при логине с неверными данными", 401, response.statusCode());
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        Response response = userClient.deleteUser(accessToken);
        assertEquals("Неверный статус код при удалении пользователя", 202, response.statusCode());
    }
}