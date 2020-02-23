import java.awt.*;

public class Rectangular extends Shape {

    public Rectangular(Rectangle rectangle, Color color, boolean solid) {
        super(rectangle, color, solid);
    }

    @Override
    public void draw(Graphics g) {
        if(this.getSolid()) {
            g.fillRect(this.x, this.y, this.width, this.height);
        } else {
            g.drawRect(this.x, this.y, this.width, this.height);
        }

    }
}
