/**
 * Oval.java
 * Brian Yu
 * 2/23/2020
 * This class inherits the Shape class and defines an rectangular shape with width, height, x/y coordinates, color, and fill.
 * It also provides a method to draw the rectangular object.
 */
import java.awt.*;

public class Rectangular extends Shape {
    //Constructor that creates Rectangular object with parameters for dimensions, coordinates, color, and fill.
    public Rectangular(Rectangle rectangle, Color color, boolean solid) {
        super(rectangle, color, solid);
    }
    //Defined abstract method from superclass to draw rectangle object on passed Graphics object
    @Override
    public void draw(Graphics g) {
        if(this.getSolid()) {
            g.fillRect(this.x, this.y, this.width, this.height);
        } else {
            g.drawRect(this.x, this.y, this.width, this.height);
        }

    }
}
