package ru.techcoredev.store.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.User;

import java.util.List;

public interface UsersDAO {
    void insertUser(User user);

    void deleteUser(int userId);

    void updateUser(User user);

    List<User> getUsers();
}
