package ru.techcoredev.store.objects;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    public User(int userId, String email, Role role, String password) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public User() {
        this.userId = 0;
        this.email = "null email";
        this.password = "null password";
        this.role = Role.NO_USER;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, Role role, String password) {
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        if (getUserId() != user.getUserId()) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getRole() != user.getRole()) return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
