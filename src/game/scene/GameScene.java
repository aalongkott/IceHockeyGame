package game.scene;

import game.input.*;
import game.sharedObject.ResourceHolder;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import game.game.*;

public class GameScene extends StackPane {
	private final Image iv = new Image("background2.png");
	private NormalBall ball;
	private Sticks sticks;
	private Canvas canvas;
	private boolean isStarted = false;

	
	public GameScene(String name1, String name2) {
		canvas = new Canvas(iv.getWidth(), iv.getHeight());
		canvas.setVisible(true);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		this.setPrefWidth(iv.getWidth());
		this.setPrefHeight(iv.getHeight());
		
		this.setBackground(new Background(new BackgroundImage(this.iv, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT)));
				
		sticks = new Sticks(gc);
		ball = new NormalBall(gc);

		Player player = new Player(name1, name2);
		
		
		AnimationTimer animation = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub

				if(isStarted) {
					addListener();
					
				}else {
					playVideo();
					isStarted = true;
				}
				
				ball.move(gc);
				sticks.draw(gc);
				
				
				
				sticks.move(gc);
			
				sticks.hit(ball);		
				
				ball.bounce(gc);
				
				
				
				if(ball.isWinning(gc)) {
					if (ball.getX_coordinate() <= 60){
						player.setScore2(player.getScore2() + 1);
						System.out.println("Score: " + player.getScore1() + ", " + player.getScore2());
						System.out.println(player.getName1());
					}else if (ball.getX_coordinate() >= gc.getCanvas().getWidth()-80) {
						player.setScore1(player.getScore1() + 1);
						System.out.println("Score: " + player.getScore1() + ", " + player.getScore2());
						System.out.println(player.getName2());
					}
					reset(gc);
					isStarted = false;
				}
			}
		};
		
		animation.start();
		
		this.getChildren().add(canvas);
	
	}

	public void addListener() {
		
		canvas.setOnKeyPressed((KeyEvent event) -> {
				InputUtility.setKeyPressed(event.getCode(), true);
			});
		
		canvas.setOnKeyReleased((KeyEvent event) -> {
				InputUtility.setKeyPressed(event.getCode(), false);
			});
		
	}
	public void reset(GraphicsContext gc) {
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
		sticks.setX_pos1(sticks.getX1_reset());
		sticks.setY_pos1(sticks.getY1_reset());
		sticks.setX_pos2(sticks.getX2_reset());
		sticks.setY_pos2(sticks.getY2_reset());
		
		ball.setX_coordinate(ball.getX_reset());
		ball.setY_coordinate(ball.getY_reset());
		
		ball.setXspeed(0);
		ball.setYspeed(0);
		
	}
	
	public void playVideo() {
		ResourceHolder.threeSecWaiting.setVisible(true);
		ResourceHolder.threeSecWaiting.getMediaPlayer().play();
		ResourceHolder.threeSecWaiting.getMediaPlayer().setOnEndOfMedia(() -> {
			ResourceHolder.threeSecWaiting.setVisible(false);
		});
		
	}
	
	
}
