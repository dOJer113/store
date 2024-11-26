package ru.techcoredev.store.dbmanagers;


import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.objects.Client;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.User;
import ru.techcoredev.store.objects.fabrics.ClientFabricForRegistration;
import ru.techcoredev.store.objects.fabrics.UserFabricForRegistration;

public class ClientAndUserManager {
    private DBType dbType;

    public ClientAndUserManager(DBType dbType) {
        this.dbType = dbType;
    }

    public boolean addUserAndClient(String email, String password, String name, String surname, String phone, String address) {
        if (this.isEmailUnique(email)) {
            User user = UserFabricForRegistration.getUser(email, password);
            Client client = ClientFabricForRegistration.getClient(name, surname, phone, address);
            if (user.getRole().equals(Role.CLIENT) && !client.getName().equals(Client.NO_USER_NAME)) {
                UserDBManager userDBManager = new UserDBManager(this.dbType);
                ClientDBManager clientDBManager = new ClientDBManager(this.dbType);
                userDBManager.addUser(user);
                int userId = userDBManager.getUserIdByEmail(user.getEmail());
                client.setUserId(userId);
                clientDBManager.addClient(client);
                return true;
            }
        }
        return false;
    }

    public void deleteUserAndClient(User user, Client client) {
        UserDBManager userDBManager = new UserDBManager(this.dbType);
        ClientDBManager clientDBManager = new ClientDBManager(this.dbType);
        clientDBManager.deleteClient(client);
        userDBManager.deleteUser(user);
    }

    private boolean isEmailUnique(String email) {
        UserDBManager userDBManager = new UserDBManager(this.dbType);
        return userDBManager.getUserIdByEmail(email) == 0;
    }


}
