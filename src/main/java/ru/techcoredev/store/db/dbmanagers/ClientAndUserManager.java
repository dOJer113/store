package ru.techcoredev.store.db.dbmanagers;


import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.objects.builders.Client;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.builders.User;
import ru.techcoredev.store.objects.builders.RegistrationClientBuilder;
import ru.techcoredev.store.objects.builders.RegistrationUserBuilder;

public class ClientAndUserManager {
    private DBType dbType;

    public ClientAndUserManager(DBType dbType) {
        this.dbType = dbType;
    }

    public boolean addUserAndClient(String email, String password, String name, String surname, String phone, String address) {
        if (this.isEmailUnique(email)) {
            User user = new RegistrationUserBuilder().email(email).password(password).build();
            Client client = new RegistrationClientBuilder().name(name).surname(surname).address(address).phoneNumber(phone).build();
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
