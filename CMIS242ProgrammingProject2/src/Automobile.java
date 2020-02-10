/**
 * Automobile.java
 * Brian Yu
 * 2/9/2020
 * This class defines an automobile object which contains its make/model and purchase price.
 */
public class Automobile {
    private String makeAndModel;
    private int price;

    //constructor that allows the make and purchase price to be initialized
    public Automobile(String makeAndModel, int price) {
        this.makeAndModel = makeAndModel;
        this.price = price;
    }

    //returns base sales tax as 5% of sales price
    public double salesTax() {
        double tax = this.price*0.05;
        return tax;
    }

    //returns a string containing the make and model of the automobile, the sales price, and the sales tax
    public String toString() {
        String autoString = String.format("Make and Model: " + this.makeAndModel + "\nPrice: $" + this.price + "\nSales Tax: $");
        autoString = autoString + String.format("%.2f", this.salesTax());
        return autoString;
    }

    //getter for private instance variable MakeAndModel
    public String getMakeAndModel() {
        return makeAndModel;
    }

    //getter for private instance variable price
    public int getPrice() {
        return price;
    }

}
