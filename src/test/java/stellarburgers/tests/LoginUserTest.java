package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.api.models.User;
import stellarburgers.steps.UserSteps;
import stellarburgers.utils.DataGenerator;

import static org.junit.Assert.assertNotNull;

@DisplayName("Тесты логина пользователя")
public class LoginUserTest {
    private UserSteps userSteps;
    private User testUser;
    private String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        testUser = DataGenerator.getRandomUser();

        // Создаем пользователя для тестов
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
    @DisplayName("Вход под существующим пользователем")
    @Description("Проверка успешного входа под существующим пользователем")
    public void loginWithExistingUserTest() {
        String loginToken = userSteps.loginUserSuccessfully(testUser);
        assertNotNull("Access token при логине не должен быть null", loginToken);
    }

    @Test
    @DisplayName("Вход с неверным email")
    @Description("Проверка попытки входа с неверным email")
    public void loginWithWrongEmailTest() {
        User wrongEmailUser = new User(
                "wrong_email@test.com",
                testUser.getPassword(),
                testUser.getName()
        );
        userSteps.tryLoginWithInvalidData(wrongEmailUser);
    }

    @Test
    @DisplayName("Вход с неверным паролем")
    @Description("Проверка попытки входа с неверным паролем")
    public void loginWithWrongPasswordTest() {
        User wrongPasswordUser = new User(
                testUser.getEmail(),
                "wrong_password",
                testUser.getName()
        );
        userSteps.tryLoginWithInvalidData(wrongPasswordUser);
    }
}