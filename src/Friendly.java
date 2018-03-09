import java.awt.*;

public class Friendly extends Sprite {


    public Friendly(Color color, int x, int y, int diameter) {

        super(color, x, y, diameter, diameter);

    }

    @Override
    public void paint(Graphics g){

        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}