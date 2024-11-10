package ru.techcoredev.store.objects.fabrics;

import ru.techcoredev.store.objects.Client;

public class ClientFabricForRegistration {
    private ClientFabricForRegistration() {
    }

    public static Client getClient(String name, String surname, String phoneNumber, String address) {
        if (ClientFabricForRegistration.isValidClient(name, surname, phoneNumber, address)) {
            return new Client(name, surname, phoneNumber, address);
        }
        return new Client();
        //todo сделать оповещение что клиент пустой
    }

    private static boolean isValidClient(String name, String surname, String phoneNumber, String address) {
        return ClientFabricForRegistration.isValidName(name) && ClientFabricForRegistration.isValidName(surname) && ClientFabricForRegistration.isValidPhoneNumber(phoneNumber) && ClientFabricForRegistration.isValidAddress(address);
    }

    private static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Zа-яА-Я\\s]{2,50}$");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$");
    }

    private static boolean isValidAddress(String address) {
        return address != null && address.matches("^[a-zA-Zа-яА-Я0-9\\s\\,\\.\\-]{5,300}$");
    }
}