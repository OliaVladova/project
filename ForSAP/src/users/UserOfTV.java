package users;

import Contracts.Contract;
import channelCategories.Category;
import repository.CategoryRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class UserOfTV implements User<Contract> {
    private int id;
    private String name;
    private String password;
    private Collection<Contract> contracts;
    private Repository<Category> categories;

    public UserOfTV(int id, String name, String password) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.contracts = new ArrayList<>();
        this.categories =new CategoryRepository();
    }

    public void setId(int id) {
        if (id<=0){
            throw new IllegalArgumentException("Id cannot be below zero!");
        }

        this.id = id;
    }

    public void setName(String name) {
        if (name==null||name.trim().isEmpty()){
            throw new NullPointerException("Name is mandatory!");
        }
        this.name = name;
    }

    private void setPassword(String password) {
        if (password==null||password.trim().isEmpty()){
            throw new NullPointerException("Password is mandatory!");
        }
        this.password = password;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<Contract> getContracts() {
        return this.contracts;
    }


    public Collection<Category> getCategories() {
        return this.categories.getEntities();
    }

}
