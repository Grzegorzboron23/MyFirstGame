import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int player;
    int xVelocity;
    int yVelocity;
    int velocity=5;
    int REAL_GAME_HEIGHT;
    int HEIGHT;
    Paddle(int player,int GAME_WIDTH, int GAME_HEIGHT, int x,int WIDTH,int HEIGHT,int REAL_GAME_HEIGHT){
        super(x,GAME_HEIGHT/2,WIDTH,HEIGHT);
        this.REAL_GAME_HEIGHT =REAL_GAME_HEIGHT;
        this.HEIGHT = HEIGHT;
        this.player = player;
    }
    public void draw(Graphics g){
        if(player ==1) {
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.RED);
        }
            g.fillRect(x, y, width, height);

    }
    public void keyPressed(KeyEvent e){
        switch(player) {
            case 2:
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if(y<0){
                    this.y = y;
                }
                else {
                    this.y = y - 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(y>=REAL_GAME_HEIGHT-HEIGHT) {
                    this.y = y;
                }
                else{
                    this.y = y + 10;
                }
            }
            break;

        case 1:
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if(y<0){
                    this.y = y;
                }
                else {
                    this.y = y - 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if(y>=REAL_GAME_HEIGHT-HEIGHT) {
                    this.y = y;
                }
                else{
                    this.y = y + 10;
                }
            }

            break;
        }

    }

}
