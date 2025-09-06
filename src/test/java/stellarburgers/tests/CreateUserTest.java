package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.api.models.User;
import stellarburgers.steps.UserSteps;
import stellarburgers.utils.DataGenerator;

import static org.junit.Assert.assertNotNull;

@DisplayName("Тесты создания пользователя")
public class CreateUserTest {
    private UserSteps userSteps;
    private User testUser;
    private String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        testUser = DataGenerator.getRandomUser();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUniqueUserTest() {
        accessToken = userSteps.createUniqueUser(testUser);
        assertNotNull("Access token не должен быть null", accessToken);
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    public void createExistingUserTest() {
        // Сначала создаем пользователя
        accessToken = userSteps.createUniqueUser(testUser);
        assertNotNull("Access token не должен быть null", accessToken);

        // Пытаемся создать такого же пользователя
        userSteps.tryCreateExistingUser(testUser);
    }

    @Test
    @DisplayName("Создание пользователя без email")
    public void createUserWithoutEmailTest() {
        User userWithoutEmail = DataGenerator.getUserWithoutEmail();
        userSteps.tryCreateUserWithoutRequiredField(userWithoutEmail);
    }

    @Test
    @DisplayName("Создание пользователя без пароля")
    public void createUserWithoutPasswordTest() {
        User userWithoutPassword = DataGenerator.getUserWithoutPassword();
        userSteps.tryCreateUserWithoutRequiredField(userWithoutPassword);
    }

    @Test
    @DisplayName("Создание пользователя без имени")
    public void createUserWithoutNameTest() {
        User userWithoutName = DataGenerator.getUserWithoutName();
        userSteps.tryCreateUserWithoutRequiredField(userWithoutName);
    }
}