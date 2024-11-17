package ru.techcoredev.store.objects.fabrics;

import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.User;

public class UserFabricForRegistration {
    public static final int PASSWORD_MIN_LENGTH = 5;

    private UserFabricForRegistration() {
    }

    public static User getUser(String email, String password) {
        if (UserFabricForRegistration.isValidUser(email, password)) {
            return new User(email, Role.CLIENT, password);
        }
        return new User();
    }

    private static boolean isValidUser(String email, String password) {
        return UserFabricForRegistration.isValidEmail(email) && UserFabricForRegistration.isValidPassword(password);
    }

    private static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        boolean flag = email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
        return flag;
    }

    private static boolean isValidPassword(String password) {
        boolean flag = password.length() >= PASSWORD_MIN_LENGTH;
        return flag;
    }
}
