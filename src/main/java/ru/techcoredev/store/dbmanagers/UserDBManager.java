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

    public static boolean getUserEmailPassword(String email, String password) {
        return DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().getUserByEmailPassword(email, password);
    }

    public static String getRoleByEmail(String email){
        return DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().getRoleByEmail(email);
    }

    protected static int getUserIdByEmail(String email) {
        return DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().getUserIdByEmail(email);
    }

    protected static void addUser(User user) {
        DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().insertUser(user);
    }

    protected static void deleteUser(int userId) {
        DAOFactory.getInstance(DBType.POSTGRES).getUsersDAO().deleteUser(userId);
    }

}
