package ru.techcoredev.store.objects.builders;

public interface ClientBuilder {
    ClientBuilder name(String name);

    ClientBuilder surname(String surname);

    ClientBuilder phoneNumber(String phoneNumber);

    ClientBuilder address(String address);

    ClientBuilder id(int id);

    Client build();
}
