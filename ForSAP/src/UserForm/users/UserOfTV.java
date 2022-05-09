package UserForm.users;

import contractForm.Contracts.BaseContract;
import categoryForm.channelCategories.ChannelCategory;

import java.util.Collection;

public class UserOfTV {
    private int id;
    private String name;
    private String password;
    private String email;

    public UserOfTV( String name, String password, String email) {
        this.setName(name);
        this.setPassword(password);
        this.setEmail(email);
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be below zero!");
        }

        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("Name is mandatory!");
        }
        this.name = name;
    }

    private void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new NullPointerException("Password is mandatory!");
        }
        this.password = password;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new NullPointerException("Email cannot be null!");
        }

        this.email = email;
    }
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }



}
