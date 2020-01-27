/**
 * Weight.java
 * Brian Yu
 * 1/26/2020
 * This class defines stores a weight as pounds and ounces and provides methods to compare, add, convert to String, convert to only ounces, and normalize.
 */
public class Weight {
    // private instance variable for pounds field
    private int pounds;
    // private instance variable for ounces field
    private double ounces;
    // private constant for amount of ounces in pound
    private static final double POUND = 16;

    //public constructor that accepts pounds and ounces as parameters
    public Weight(int pounds, double ounces) {
        this.pounds = pounds;
        this.ounces = ounces;
    }
    //public instance method determines whether the instance this method is executed on is less than the weight object arguement
    public boolean lessThan (Weight lessWeight) {
        if(pounds < lessWeight.pounds) {
            return true;
        } else if (pounds > lessWeight.pounds) {
            return false;
        } else {
            if (ounces < lessWeight.ounces) {
                return true;
            } else {
                return false;
            }
        }
    }
    // public instance method that adds the weight object argument to the instance
    public void addTo (Weight addWeight) {
        this.pounds += addWeight.pounds;
        this.ounces += addWeight.ounces;
        normalize();
    }
    //public instance method that divides the instance by the divisor arguement
    public void divide (int divisor) {
        double dividedWeight = this.toOunces() / divisor;
        this.pounds = (int)(dividedWeight / POUND);
        this.ounces = dividedWeight % POUND;
        normalize();
    }
    // public instance method that converts instance to a String object
    public String toString() {
        String weightString = String.format(pounds + " lbs " + ounces + " oz");
        return weightString;
    }
    // private instance method that converts instance to ounces
    private double toOunces() {
        double weightInOunces = (pounds * POUND) + ounces;
        return weightInOunces;
    }
    // private instance method that makes sure that weight is properly displayed in pounds and ounces
    private void normalize() {
        if (ounces >= POUND) {
            double remainder = ounces - POUND;
            pounds++;
            ounces = remainder;
        }
    }

}

