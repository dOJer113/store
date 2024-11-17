package ru.techcoredev.store.dbmanagers;


import ru.techcoredev.store.objects.Client;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.User;
import ru.techcoredev.store.objects.fabrics.ClientFabricForRegistration;
import ru.techcoredev.store.objects.fabrics.UserFabricForRegistration;

public class ClientAndUserManager {
    public static boolean addUserAndClient(String email, String password, String name, String surname, String phone, String address) {
        if (ClientAndUserManager.isEmailUnique(email)) {
            User user = UserFabricForRegistration.getUser(email, password);
            Client client = ClientFabricForRegistration.getClient(name, surname, phone, address);
            if (user.getRole().equals(Role.CLIENT) && !client.getName().equals(Client.NO_USER_NAME)) {
                UserDBManager.addUser(user);
                int userId = UserDBManager.getUserIdByEmail(user.getEmail());
                client.setUserId(userId);
                ClientDBManager.addClient(client);
                return true;
            }
        }
        return false;
    }

    public static void deleteUserAndClient(int userId) {
        ClientDBManager.deleteClient(userId);
        UserDBManager.deleteUser(userId);
    }

    private static boolean isEmailUnique(String email) {
        return UserDBManager.getUserIdByEmail(email) == 0;
    }


}
