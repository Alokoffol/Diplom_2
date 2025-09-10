package stellarburgers.utils;

import com.github.javafaker.Faker;
import stellarburgers.api.models.User;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    public static User getRandomUser() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12, true, true, true);
        String name = faker.name().fullName();

        return new User(email, password, name);
    }

    public static User getUserWithoutEmail() {
        String password = faker.internet().password(8, 12, true, true, true);
        String name = faker.name().fullName();

        User user = new User();
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    public static User getUserWithoutPassword() {
        String email = faker.internet().emailAddress();
        String name = faker.name().fullName();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        return user;
    }

    public static User getUserWithoutName() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12, true, true, true);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static User getInvalidUser() {
        String email = faker.internet().emailAddress();
        String password = "wrongpassword";
        String name = faker.name().fullName();

        return new User(email, password, name);
    }
}