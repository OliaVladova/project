package suppliers;

import Contracts.Contract;
import channels.TVChannel;
import repository.ChannelRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class BaseSupplier implements Supplier<Contract>{
    private int id;
    private String name;
    private double price;
    private String deadline;
    private Repository<TVChannel> channels;
    private Collection<Contract> contracts;

    public BaseSupplier(int id, String name,double price, String deadline) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.deadline = deadline;
        this.channels = new ChannelRepository();
        this.contracts = new ArrayList<>();
    }

    @Override
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

    @Override
    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException("Price cannot be below or equal to zero!");
        }
        this.price = price;
    }


    @Override
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public Collection<TVChannel> getChannels() {
        return this.channels.getEntities();
    }
    public Collection<Contract> getContracts() {
        return this.contracts;
    }

}
