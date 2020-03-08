/**
 * Property.java
 * Brian Yu
 * 3/8/2020
 * This class implements ChangeableState interface and defines Property object to store property information
 */
public class Property<T extends Enum<T>> implements ChangeableState<T> {
    private String propertyAddress;
    private int numberOfBedrooms, sqFeet, price;
    private Status saleStatus;
    //Constructor that accepts 4 parameters and defines status as FOR_SALE
    public Property(String propertyAddress, int numberOfBedrooms, int sqFeet, int price) {
        this.propertyAddress = propertyAddress;
        this.numberOfBedrooms = numberOfBedrooms;
        this.sqFeet = sqFeet;
        this.price = price;
        this.saleStatus = Status.FOR_SALE;
    }
    //Overridden method that changes status of property
    @Override
    public void changeState(T inputStatus) {
       this.saleStatus = (Status)inputStatus;
    }
    //Overriden method that returns string of property information
    @Override
    public String toString() {
        return new String("Property Address: " + this.propertyAddress + "\nNumber of Bedrooms: " + this.numberOfBedrooms
        + "\nSquare Feet: " + this.sqFeet + "\nPrice: $" + this.price + "\nStatus: " + this.saleStatus);
    }
}
