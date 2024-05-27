package netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("en"));
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String generateRandomLogin() {
        return faker.name().username();
    }

    public static String generateRandomPassword() {
        return faker.internet().password();
    }
    public static AuthInfo generateRandomPassworForUserVasya() {
        return new AuthInfo("vasya", generateRandomPassword());
    }
    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        private String code;
    }

}