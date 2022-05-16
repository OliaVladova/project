package supplierForm.suppliers;

public class BaseSupplier {
    private String name;
    private double price;

    public BaseSupplier(String name,double price) {
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
