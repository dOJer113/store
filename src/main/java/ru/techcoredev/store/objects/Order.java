package ru.techcoredev.store.objects;

import java.sql.Date;

public class Order {
    private int number;
    private int userId;
    private Date registrationDate;
    private Date closingDate;
    private Status status;

    public Order(int number, int userId, Date registrationDate, Date closingDate, Status status) {
        this.number = number;
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.closingDate = closingDate;
        this.status = status;
    }

    public Order(int userId, Date registrationDate, Date closingDate, Status status) {
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.closingDate = closingDate;
        this.status = status;
    }

    public Order() {
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        if (getNumber() != order.getNumber()) return false;
        if (getUserId() != order.getUserId()) return false;
        if (getRegistrationDate() != null ? !getRegistrationDate().equals(order.getRegistrationDate()) : order.getRegistrationDate() != null)
            return false;
        if (getClosingDate() != null ? !getClosingDate().equals(order.getClosingDate()) : order.getClosingDate() != null)
            return false;
        return getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = 31 * result + getUserId();
        result = 31 * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
        result = 31 * result + (getClosingDate() != null ? getClosingDate().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", userId=" + userId +
                ", registrationDate=" + registrationDate +
                ", closingDate=" + closingDate +
                ", status=" + status +
                '}';
    }
}
