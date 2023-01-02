import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000, GAME_HEIGHT = 555;
    static final int PADLLE_WIDTH = 25, PADDLE_HEIGHT = 100;
    static final int SCORE_NUMBER_WIDTH = 50, SCORE_NUMBER_HEIGHT = 50, SC0RE_BALL_DIMENSION = 10;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    int BALL_DIMENSION = 25;
    Paddle paddle1, paddle2;
    Thread gameThread;
    Score score;
    Ball ball;
    Image image;
    Graphics graphics;


    Panel() {
        newBall();
        newPaddle(1); // one means first player on the left size two second player
        newPaddle(2);
        newScore();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(new AL());
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newPaddle(int a) {
        if (a == 1) {
            paddle1 = new Paddle(a, GAME_WIDTH, GAME_HEIGHT - PADDLE_HEIGHT / 2, 0, PADLLE_WIDTH, PADDLE_HEIGHT, GAME_HEIGHT);
        } else {
            paddle2 = new Paddle(a, GAME_WIDTH, GAME_HEIGHT - PADDLE_HEIGHT / 2, GAME_WIDTH - PADLLE_WIDTH, PADLLE_WIDTH, PADDLE_HEIGHT,GAME_HEIGHT);
        }

    }
    public void move() {
        ball.move();
    }
    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public void newBall() {
        ball = new Ball(GAME_WIDTH / 2 - BALL_DIMENSION / 2, GAME_HEIGHT / 2 - BALL_DIMENSION / 2, BALL_DIMENSION);
    }

    public void newScore() {
        score = new Score(GAME_WIDTH, SCORE_NUMBER_WIDTH, SCORE_NUMBER_HEIGHT, SC0RE_BALL_DIMENSION);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight()); // create image width and height of the panel
        graphics = image.getGraphics();
        draw(graphics); // call draw method
        g.drawImage(image, 0, 0, this); // 0,0 start corner to start this == JPanel
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
    }
    public void checkCollision() {
        //bounce ball off top & bottom window edges
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIMENSION) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(paddle1)){
            ball.setXDirection(-ball.xVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.setXDirection(-ball.xVelocity);
        }
        if(ball.x <=0) {
            score.player1++;
            newPaddle(1);
            newPaddle(2);
            newBall();
            System.out.println("Player 2: "+score.player2);
        }
        if(ball.x >= GAME_WIDTH-BALL_DIMENSION) {
            score.player2++;
            newPaddle(1);
            newPaddle(2);
            newBall();
            System.out.println("Player 1: "+score.player1);
        }

    }

    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
            repaint();
        }
    }
}

