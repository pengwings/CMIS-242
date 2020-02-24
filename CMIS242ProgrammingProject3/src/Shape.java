/**
 * Shape.java
 * Brian Yu
 * 2/23/2020
 * This abstract class inherits the Rectangle class and defines a shape with width, height, x/y coordinates, color, and fill.
 */
import java.awt.*;

public abstract class Shape extends Rectangle {
    private Color color;
    private boolean solid;
    private static int numberOfShapes;
    //Constructor that accepts sets dimensions, coordinates, color, fill, and adds number of shapes created so far.
    public Shape(Rectangle rectangle, Color color, boolean solid) {
        super(rectangle);
        this.color = color;
        this.solid = solid;
        numberOfShapes++;
    }
    //Instance method that sets the color of the next shape that is drawn to the color of the current shape
    public void setColor(Graphics g) {
        g.setColor(color);
    }
    //Getter for private instance variable for fill (e.g. solid or hollow)
    public boolean getSolid() {
        return solid;
    }
    //Getter for private class variable for number of shapes created so far.
    public static int getNoOfShapes() {
        return numberOfShapes;
    }
    //Abstract method to be defined in Oval/Rectangular subclasses
    public abstract void draw(Graphics g);

}
