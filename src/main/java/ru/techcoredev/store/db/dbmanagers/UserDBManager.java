package ru.techcoredev.store.db.dbmanagers;

import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.builders.Client;
import ru.techcoredev.store.objects.builders.User;

import java.util.List;

public class UserDBManager {
    private DBType dbType;

    public UserDBManager(DBType dbType) {
        this.dbType = dbType;
    }

    public void updateUser(User user) {
        DAOFactory.getInstance(dbType).getUsersDAO().updateUser(user);
    }

    public List<User> getUsers() {
        return DAOFactory.getInstance(dbType).getUsersDAO().getUsers();
    }

    public User getUserEmailPassword(String email, String password) {
        return DAOFactory.getInstance(dbType).getUsersDAO().getUserByEmailPassword(email, password);
    }

    public boolean addUserAndClient(User user, Client client) {
        return DAOFactory.getInstance(dbType).getUsersDAO().addUserAndClient(user, client);
    }

    public Role getRoleByEmail(String email) {
        return DAOFactory.getInstance(dbType).getUsersDAO().getRoleByEmail(email);
    }

    public int getUserIdByEmail(String email) {
        return DAOFactory.getInstance(dbType).getUsersDAO().getUserIdByEmail(email);
    }

    public int addUser(User user) {
        return DAOFactory.getInstance(dbType).getUsersDAO().insertUser(user);
    }

    protected void deleteUser(User user) {
        DAOFactory.getInstance(dbType).getUsersDAO().deleteUser(user);
    }

}
