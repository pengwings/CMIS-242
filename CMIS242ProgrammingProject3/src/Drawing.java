import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {
    private Shape shape;


    @Override
    protected void paintComponent(Graphics g) {
        if(shape != null) {
            shape.draw(g);
            String numberOfShapesString = Integer.toString(Shape.getNoOfShapes());
            g.drawString(numberOfShapesString,0,0);
        } else {
            System.out.println("WHY IS SHAPE NOT DEFINED YET");
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,200);
    }

    public void drawShape(Shape shape) throws OutsideBounds {
        if(shape.width > this.getWidth() || shape.height > this.getHeight()){
            throw new OutsideBounds();
        } else {
            this.shape = shape;
            repaint();
        }
    }
}
