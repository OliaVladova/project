package Contracts;

import channelCategories.ChannelCategory;
import channels.Channel;

import java.util.ArrayList;
import java.util.Collection;

public class BaseContract {
    private int id;
    private String date;
    private String deadline;
    private int userId;
    private int supplierId;
    private Collection<Channel> channels;
    private Collection<ChannelCategory> categories;

    public BaseContract(int id, int userId, int supplierId, String date,String deadline) {
        this.setId(id);
        this.date = date;
        this.deadline = deadline;
        this.setUserId(userId);
        this.setSupplierId(supplierId);
        this.categories = new ArrayList<>();
        this.channels = new ArrayList<>();
    }

    private void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less or equal to zero!");
        }
        this.id = id;
    }

    private void setUserId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less or equal to zero!");
        }
        this.userId = id;
    }

    private void setSupplierId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less or equal to zero!");
        }
        this.supplierId = id;
    }

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }
}
