package ru.techcoredev.store.dbmanagers;


import ru.techcoredev.store.objects.Client;
import ru.techcoredev.store.objects.User;

public class ClientAndUserManager {
    public static void addUser(User user, Client client) {
        if (ClientAndUserManager.isEmailUnique(user.getEmail())) {
            UserDBManager.addUser(user);
            int userId = UserDBManager.getUserIdByEmail(user.getEmail());
            client.setUserId(userId);
            ClientDBManager.addClient(client);
        } else {
            //todo сделать обработку ситуации, что клиент с такой почтой уже есть
        }
    }

    public static void deleteUser(int userId) {
        ClientDBManager.deleteClient(userId);
        UserDBManager.deleteUser(userId);
    }

    private static boolean isEmailUnique(String email) {
        return UserDBManager.getUserIdByEmail(email) != 0;
    }


}
