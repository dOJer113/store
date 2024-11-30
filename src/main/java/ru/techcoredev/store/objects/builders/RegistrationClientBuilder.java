package ru.techcoredev.store.objects.builders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

public class RegistrationClientBuilder implements ClientBuilder {
    private static final Logger logger = LogManager.getLogger(RegistrationClientBuilder.class);
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private int id = 0;


    @Override
    public ClientBuilder name(String name) {
        this.name = this.convertStringToUtf8(name);
        return this;
    }

    @Override
    public ClientBuilder surname(String surname) {
        this.surname = this.convertStringToUtf8(surname);
        ;
        return this;
    }

    @Override
    public ClientBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public ClientBuilder address(String address) {
        this.address = this.convertStringToUtf8(address);
        return this;
    }

    @Override
    public ClientBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Client build() {
        if (this.isValidClient()) {
            Client client;
            if (id != 0) {
                client = new Client(id, name, surname, phoneNumber, address);
            } else {
                client = new Client(name, surname, phoneNumber, address);
            }
            return client;
        }
        logger.info("Was created null client for registration name and surname: " + name + " " + surname);
        return new Client();
    }

    private boolean isValidClient() {
        return isValidName() && isValidSurName() && isValidPhoneNumber() && isValidAddress();
    }

    private boolean isValidName() {
        return name != null && name.matches("^[a-zA-Zа-яА-Я\\s]{2,50}$");
    }

    private boolean isValidSurName() {
        return surname != null && surname.matches("^[a-zA-Zа-яА-Я\\s]{2,50}$");
    }

    private boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$");
    }

    private boolean isValidAddress() {
        return address != null && address.matches("^[a-zA-Zа-яА-Я0-9\\s\\,\\.\\-]{5,300}$");
    }

    private String convertStringToUtf8(String value) {
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
