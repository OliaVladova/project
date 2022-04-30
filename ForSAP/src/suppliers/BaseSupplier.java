package suppliers;

import Contracts.BaseContract;
import channels.Channel;
import repository.ChannelRepository;

import java.util.ArrayList;
import java.util.Collection;

public class BaseSupplier {
    private int id;
    private String name;
    private double price;
    private ChannelRepository channels;
    private Collection<BaseContract> contracts;

    public BaseSupplier(int id, String name,double price) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.channels = new ChannelRepository();
        this.contracts = new ArrayList<>();
    }


    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        if (id<=0){
            throw new IllegalArgumentException("Id cannot be below or equal to zero!");
        }else {
            this.id = id;
        }
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name==null|| name.trim().isEmpty()){
            throw new NullPointerException("Name cannot be null!");
        }
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException("Price cannot be below or equal to zero!");
        }
        this.price = price;
    }


    public Collection<Channel> getChannels() {
        return this.channels.getEntities();
    }
    public Collection<BaseContract> getContracts() {
        return this.contracts;
    }

}
