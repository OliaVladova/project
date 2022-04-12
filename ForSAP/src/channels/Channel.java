package channels;

public class Channel implements TVChannel{
    private int id;
    private String name;
    private double price;

    public Channel(int id, String name, double price) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        if (id<=0){
            throw new IllegalArgumentException("Id cannot be less or equal to zero!");
        }
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException("Invalid name!");
        }
        this.name = name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if (price<=0){
            throw new IllegalArgumentException("Price cannot be less or equal of zero!");
        }
        this.price = price;
    }
}
