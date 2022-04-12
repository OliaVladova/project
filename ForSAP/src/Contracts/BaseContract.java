package Contracts;

import channelCategories.ChannelCategory;
import channels.TVChannel;

import java.util.ArrayList;
import java.util.Collection;

public class BaseContract implements Contract{
    private int id;
    private String date;
    private Collection<TVChannel>channels;
    private Collection<ChannelCategory>categories;

    public BaseContract(int id, String date) {
        this.setId(id);
        this.date = date;
        this.categories = new ArrayList<>();
        this.channels = new ArrayList<>();
    }

    private void setId(int id) {
        if (id<=0){
            throw new IllegalArgumentException("Id cannot be less or equal to zero!");
        }
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getDate() {
        return this.date;
    }
}
