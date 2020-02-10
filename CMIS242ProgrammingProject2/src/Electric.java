/**
 * Electric.java
 * Brian Yu
 * 2/9/2020
 * This subclass of Automobile defines an Electric object which contains its make/model, purchase price, and weight.
 */
public class Electric extends Automobile {
    private int pounds;

    //constructor that allows make/model, price, and weight to be initialized
    public Electric(String makeAndModel, int price, int pounds) {
        super(makeAndModel, price);
        this.pounds = pounds;
    }

    //overriden method to return base sales tax with discount based on weight of vehicle
    @Override
    public double salesTax() {
        double electricTax;
        if (pounds < 3000) {
            electricTax = (this.getPrice()*0.05) -  200;
        } else {
            electricTax = (this.getPrice()*0.05) - 150;
        }
        return electricTax;
    }

    //overriden method to return string with make/model, weight, price, and sales tax
    @Override
    public String toString() {
        String electricString = String.format("Make and Model: " + this.getMakeAndModel() + "\nWeight: " + pounds + "\nPrice: $" + this.getPrice() + "\nSales Tax: $");
        electricString = electricString + String.format("%.2f", this.salesTax());
        return electricString;
    }
}
