package categoryForm.channelCategories;

public class ChannelCategory {
    private String type;
    private double price;

    public ChannelCategory(String type, double price) {
        this.price = price;
        this.type = type;
    }


    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }



}
