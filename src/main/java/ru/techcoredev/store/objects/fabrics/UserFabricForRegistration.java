package ru.techcoredev.store.objects.fabrics;

import ru.techcoredev.store.objects.User;

public class UserFabricForRegistration {
    public static final int PASSWORD_MIN_LENGTH = 5;

    private UserFabricForRegistration() {
    }

    public static User getUser(String email, String password) {
        if (UserFabricForRegistration.isValidUser(email, password)) {
            return new User(email, password);
        }
        return new User();
        //todo сделать оповещение что клиент пустой
    }

    private static boolean isValidUser(String email, String password) {
        return UserFabricForRegistration.isValidEmail(email) && UserFabricForRegistration.isValidPassword(password);
    }

    private static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private static boolean isValidPassword(String password) {
        return password.length() > PASSWORD_MIN_LENGTH;
    }
}
