package channelCategories;

import channels.Channel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ChannelCategory {
    private int id;
    private String type;
    private double price;
    private Collection<Channel> channels;

    public ChannelCategory(int id, String type, double price) {
        this.setId(id);
        this.setType(type);
        this.setPrice(price);
        this.channels = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }

    public Collection<Channel> getChannels() {
        return Collections.unmodifiableCollection(this.channels);
    }

    private void setId(int id) {
        if (id<=0){
            throw new IllegalArgumentException("Input valid id!");
        }
        this.id = id;
    }

    private void setType(String type) {
        if (type==null||type.trim().isEmpty()){
            throw new NullPointerException("Type cannot be empty!");
        }
        this.type = type;
    }

    private void setPrice(double price) {
        if (price<=0){
            throw new IllegalArgumentException("Price cannot be below or equal to zero!");
        }
        this.price = price;
    }
}
