import java.awt.*;

public abstract class Shape extends Rectangle {
    private Color color;
    private boolean solid;
    private static int numberOfShapes;

    public Shape(Rectangle rectangle, Color color, boolean solid) {
        this.color = color;
        this.solid = solid;
        numberOfShapes++;
    }

    public void setColor(Graphics g) {
        g.setColor(color);
    }

    public boolean getSolid() {
        return solid;
    }

    public static int getNoOfShapes() {
        return numberOfShapes;
    }

    public abstract void draw(Graphics g);

}
