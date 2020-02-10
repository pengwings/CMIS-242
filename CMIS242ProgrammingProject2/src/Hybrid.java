/**
 * Hybrid.java
 * Brian Yu
 * 2/9/2020
 * This subclass of Automobile defines an Hybrid object which contains its make/model, purchase price, and mpg.
 */
public class Hybrid extends Automobile {
    private int mpg;

    //constructor that allows make/model, price, and mpg to be initialized
    public Hybrid(String makeAndModel, int price, int mpg) {
        super(makeAndModel, price);
        this.mpg = mpg;
    }

    //overriden method to return base sales tax with discount based on mpg of vehicle
    @Override
    public double salesTax() {
        double hybridTax;
        if(mpg<40) {
            hybridTax = (this.getPrice()*0.05)-100;
        } else {
            hybridTax = (this.getPrice()*0.05)-100-2*(mpg-40);
        }
        return hybridTax;
    }

    //overriden method to return string with make/model, mpg, price, and sales tax
    @Override
    public String toString() {
        String hybridString = String.format("Make And Model: " + this.getMakeAndModel() + "\nMPG: " + mpg + "\nPrice: $" + this.getPrice() + "\nSales Tax: $");
        hybridString = hybridString + String.format("%.2f", this.salesTax());
        return hybridString;
    }
}
