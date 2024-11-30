package ru.techcoredev.store.db.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.builders.User;

import java.util.List;

public interface UsersDAO {
    void insertUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    int getUserIdByEmail(String email);

    List<User> getUsers();

    User getUserByEmailPassword(String email, String password);

    Role getRoleByEmail(String email);
}
