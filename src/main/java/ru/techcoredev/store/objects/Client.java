package ru.techcoredev.store.objects;

public class Client {
    public static final String NO_USER_NAME = "no_user";
    private int userId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    public Client(String name, String surname, String phoneNumber, String address) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Client(int userId, String name, String surname, String phoneNumber, String address) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Client() {
        this.userId = 0;
        this.name = NO_USER_NAME;
        this.surname = "no_surname";
        this.phoneNumber = "no_phone";
        this.address = "no_address";
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getUserId() != client.getUserId()) return false;
        if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(client.getSurname()) : client.getSurname() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(client.getPhoneNumber()) : client.getPhoneNumber() != null)
            return false;
        return getAddress() != null ? getAddress().equals(client.getAddress()) : client.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
