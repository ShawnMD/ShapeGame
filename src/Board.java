import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {


    Timer timer;
    List<Sprite> actors = new ArrayList<Sprite>();

    final int friendlyNum = 10;
    final int enemyNum = 5;

    private boolean isFired = false;

    private long lastMoment, currentMoment;


    public Board(){
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.BLACK);

        timer = new Timer(1000/60, this);
        timer.start();
        lastMoment = System.currentTimeMillis();

    }

    public void setup() {


        actors.add(0, new Friendly(Color.yellow, getWidth()/2, getHeight()/2, 15));


        for (int i = 0; i < friendlyNum; i++) {
            actors.add(new Friendly(Color.red, getWidth() / 2, getHeight() / 2, 5));
        }
        for (int i = 0; i < enemyNum; i++) {
            actors.add(new Enemies(Color.CYAN, getWidth() / 4, getHeight() / 4, 10, 20));
        }



    }

    public void checkCollisions() {
        for (int i = 0; i < actors.size(); i++) {
            for (int j = 0; j < actors.size(); j++) {
                if(i != j){
                        if(actors.get(i).collidesWith(actors.get(j))){
                            if(actors.get(i) instanceof Friendly && actors.get(j) instanceof Friendly){


                            actors.get(i).bounce();
                            actors.get(j).bounce();

                        }

                        if(actors.get(i) instanceof Enemies && actors.get(j) instanceof Enemies){

                            actors.get(i).bounce();
                            actors.get(j).bounce();
                        }
                    }
                }
            }
            if(actors.get(i) instanceof Bullet){
                if(((Bullet) actors.get(i)).isRemove()){
                    actors.remove(i);
                }
            }
        }
    }

    public void setPlayerPos(int x, int y){
        actors.get(0).setPosition(x, y);
    }

    public void shootBullet(){

        currentMoment = System.currentTimeMillis();
        if(currentMoment - lastMoment > 100) {
            actors.add((new Bullet(Color.green, actors.get(0).x, actors.get(0).y, 5, 5)));
            lastMoment = System.currentTimeMillis();
        }

    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < actors.size(); i++){
            actors.get(i).paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        checkCollisions();

        for(int i = 1; i < actors.size(); i++){
            actors.get(i).move(getHeight(), getWidth());
        }
        repaint();
    }
}
