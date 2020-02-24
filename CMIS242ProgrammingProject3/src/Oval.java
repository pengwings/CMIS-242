/**
 * Oval.java
 * Brian Yu
 * 2/23/2020
 * This class inherits the Shape class and defines an oval shape with width, height, x/y coordinates, color, and fill.
 * It also provides a method to draw the oval object.
 */
import java.awt.*;

public class Oval extends Shape {
    //Constructor that creates Oval object with parameters for dimensions, coordinates, color, and fill.
    public Oval(Rectangle rectangle, Color color, boolean solid) {
        super(rectangle, color, solid);
    }
    //Defined abstract method from superclass to draw oval object on passed Graphics object
    @Override
    public void draw (Graphics g) {
        if(this.getSolid()) {
            g.fillOval(this.x, this.y, this.width, this.height);
        } else {
            g.drawOval(this.x, this.y, this.width, this.height);
        }

    }
}
