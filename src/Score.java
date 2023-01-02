import java.awt.*;

public class Score {
    int gameWidth , width,height,ballSize;
    int player1;
    int player2;
    Score(int gameWidth,int width,int height,int ballSize){
    this.gameWidth = gameWidth/2;
    this.width = gameWidth;
    this.height = height;
    this.ballSize = ballSize;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(gameWidth-ballSize/2,17,ballSize,ballSize);
        g.fillOval(gameWidth-ballSize/2,34,ballSize,ballSize);
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 50;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.drawString(String.valueOf(player1/10), gameWidth+10, 50);
        g2.drawString(String.valueOf(player1%10), gameWidth+40, 50);
        g2.drawString(String.valueOf(player2%10), gameWidth-40, 50);
        g2.drawString(String.valueOf(player2/10), gameWidth-70, 50);

    }
}
