import java.awt.*;

public class Sprite implements Move, Paint {

    Board board;
    Color color;
    int x, y, width, height;

    double dx, dy;

    final int SPEED = 10;
    /**
     * This constructor builds an object from the Sprite class. These will be the main actors of our movie
     *
     * @param color  This determines color of actor
     * @param x      This specifies the initial x location of our actor
     * @param y      This specifies the initial y locaton of our actor
     * @param width  This specifies the width determine size of our actor
     * @param height This specifies the height determine size of our actor
     */

    public Sprite(Color color, int x, int y, int width, int height) {

        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        while (dx < 1 || dy < 1) {
            double angle = 2 * Math.PI * Math.random();
            double speed = 1 + SPEED * Math.random();
            dx = Math.cos(angle) * speed;
            dy = Math.sin(angle) * speed;
        }


    }

    /**
     *
     * @param other Specifies the Sprite entity that is being collided with
     * @return      Gives a boolean value, true if the entities collided, false if not
     */

    //me.collidsWith(you)


    public boolean collidesWith(Sprite other){

        return this.getBounds().intersects(other.getBounds());

    }

    public void bounce(){
        dx*= -1;
        dy*= -1;
    }





    @Override
    public void move(int boardHeight, int boardWidth) {

        //Predictive movement
        double nextLeft = x + dx;
        double nextRight = x + width + dx;
        double nextTop = y + dy;
        double nextBottom = y + height + dy;

        if(nextTop <= 0 || nextBottom > boardHeight){
            dy *= -1;
        }

        if(nextLeft <= 0 || nextRight > boardWidth){
            dx *= -1;
        }

        x += dx;
        y += dy;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x + dx), (int) (y + dy), width, height);
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void setPosition(int x, int y) {

        this.x = x;
        this.y = y;
    }
}
