import java.awt.*;

public class Bullet extends Sprite {

    private boolean isRemove;

    public Bullet(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
        isRemove = false;
    }

    @Override
    public void move(int boardHeight, int boardWidth){
        if(x < 0 || x > boardWidth){
            isRemove = true;
        }
        if(y < 0 || y > boardHeight){
            isRemove = true;
        }

        y+= 0;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public boolean isRemove(){
        return isRemove;
    }
}
