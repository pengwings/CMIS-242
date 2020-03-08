public class Property<T extends Enum<T>> implements ChangeableState<T> {
    private String propertyAddress;
    private int numberOfBedrooms, sqFeet, price;
    private Status saleStatus;

    public Property(String propertyAddress, int numberOfBedrooms, int sqFeet, int price) {
        this.propertyAddress = propertyAddress;
        this.numberOfBedrooms = numberOfBedrooms;
        this.sqFeet = sqFeet;
        this.price = price;
        this.saleStatus = Status.FOR_SALE;
    }

    @Override
    public void changeState(T saleStatus) {
        
    }

    @Override
    public String toString() {
        return new String("Property Address: " + this.propertyAddress + "\nNumber of Bedrooms: " + this.numberOfBedrooms
        + "\nSquare Feet: " + this.sqFeet + "\nPrice: " + this.price + "\nStatus: " + this.saleStatus);
    }

}
