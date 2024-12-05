package ru.techcoredev.store.objects;

import java.util.Date;
import java.util.List;

public class OrderDetails {
    private int number;
    private Date registrationDate;
    private Date closingDate;
    private Status status;
    private String clientName;
    private String clientSurname;
    private String clientPhoneNumber;
    private String clientAddress;
    private List<Product> productList;

    public OrderDetails(int number, Date registrationDate, Date closingDate, Status status, String clientName, String clientSurname, String clientPhoneNumber, String clientAddress, List<Product> productList) {
        this.number = number;
        this.productList = productList;
        this.registrationDate = registrationDate;
        this.closingDate = closingDate;
        this.status = status;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientAddress = clientAddress;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public List<Product> getProductList() {
        return productList;
    }
}