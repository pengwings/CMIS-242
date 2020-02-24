/**
 * Drawing.java
 * Brian Yu
 * 2/23/2020
 * This class inherits the JPanel class and defines a drawing panel with overwritten methods
 * for painting objects of the Shape class and its subclasses.
 */
import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {
    private Shape shape;
    //Overridden method from JPanel that paints the appropriate Oval or Rectangular object and number of Shapes created so far on panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(shape != null) {
            String numberOfShapesString = Integer.toString(Shape.getNoOfShapes());
            g.drawString(numberOfShapesString,10,30);
            shape.setColor(g);
            shape.draw(g);
        }
    }
    //Overriden method from JPanel that returns the dimension of 200x200
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,200);
    }
    //Instance method that ensures that the shape fits the bounds of the panel (and throws an exception if it does) before saving the passed shape to the instance variable and repaints the panel
    public void drawShape(Shape shape) throws OutsideBounds {
        if ((shape.x + shape.width) > this.getWidth() || (shape.y + shape.height) > this.getHeight()) {
            throw new OutsideBounds();
        } else {
            this.shape = shape;
            repaint();
        }
    }
}
