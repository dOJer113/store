package ru.techcoredev.store.dbmanagers;

import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.objects.User;

import java.util.List;

public class UserDBManager {
    public static void updateUser(User user) {
        DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().updateUser(user);
    }

    public static List<User> getUsers() {
        return DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().getUsers();
    }

    protected static void addUser(User user) {
        DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().insertUser(user);
    }

    protected static void deleteUser(int userId) {
        DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().deleteUser(userId);
    }

}
