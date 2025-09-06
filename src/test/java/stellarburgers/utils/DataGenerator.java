package stellarburgers.utils;

import stellarburgers.api.models.User;
import java.util.UUID;

public class DataGenerator {

    public static User getRandomUser() {
        String randomString = UUID.randomUUID().toString().substring(0, 8);
        User user = new User();
        user.setEmail("testuser_" + randomString + "@example.com");
        user.setPassword("password_" + randomString);
        user.setName("User_" + randomString);
        return user;
    }

    public static User getUserWithoutEmail() {
        User user = new User();
        user.setPassword("password123");
        user.setName("TestUser");
        return user;
    }

    public static User getUserWithoutPassword() {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setName("TestUser");
        return user;
    }

    public static User getUserWithoutName() {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        return user;
    }

    public static User getInvalidUser() {
        User user = new User();
        user.setEmail("invalid@example.com");
        user.setPassword("wrongpassword");
        user.setName("InvalidUser");
        return user;
    }
}