package ru.techcoredev.store.objects.builders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.techcoredev.store.objects.Role;

public class RegistrationUserBuilder implements UserBuilder {
    private String password;
    private String email;
    private int id;
    private Role role;
    private static final Logger logger = LogManager.getLogger(RegistrationUserBuilder.class);
    public static final int PASSWORD_MIN_LENGTH = 5;

    @Override
    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilder role(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public UserBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public User build() {
        if (this.isValidUser()) {
            if (this.role == null) {
                this.role = Role.CLIENT;
            }
            User user;
            if (id != 0) {
                user = new User(id, email, role, password);
            } else {
                user = new User(id, email, role, password);
            }
            return user;
        }
        logger.info("Was created null user, id and email: " + this.id + " " + this.email);
        return new User();
    }


    private boolean isValidUser() {
        return this.isValidEmail() && this.isValidPassword();
    }

    private boolean isValidEmail() {
        if (email == null) {
            return false;
        }
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
    }

    private boolean isValidPassword() {
        return password.length() >= PASSWORD_MIN_LENGTH;
    }
}
