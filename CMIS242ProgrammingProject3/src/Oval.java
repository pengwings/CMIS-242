import java.awt.*;

public class Oval extends Shape {

    public Oval(Rectangle rectangle, Color color, boolean solid) {
        super(rectangle, color, solid);
    }

    @Override
    public void draw (Graphics g) {
        if(this.getSolid()) {
            g.fillOval(this.x, this.y, this.width, this.height);
        } else {
            g.drawOval(this.x, this.y, this.width, this.height);
        }

    }
}
