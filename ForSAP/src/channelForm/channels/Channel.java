package channelForm.channels;

public class Channel {
    private String name;
    private double price;

    public Channel(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }


    public double getPrice() {
        return this.price;
    }

}
