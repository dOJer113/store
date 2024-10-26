package ru.techcoredev.store.dbmanagers;


import ru.techcoredev.store.objects.Client;
import ru.techcoredev.store.objects.User;

public class ClientAndUserManager {
    public static void addUser(User user, Client client) {
        UserDBManager.addUser(user);
        ClientDBManager.addClient(client);
    }

    public static void deleteUser(int userId) {
        ClientDBManager.deleteClient(userId);
        UserDBManager.deleteUser(userId);
    }


}
