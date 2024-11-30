package ru.techcoredev.store.objects.builders;

import ru.techcoredev.store.objects.Role;

public interface UserBuilder {
    UserBuilder password(String password);

    UserBuilder email(String email);

    UserBuilder role(Role role);

    UserBuilder id(int id);

    User build();
}
