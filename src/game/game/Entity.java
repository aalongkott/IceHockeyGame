package game.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public abstract class Entity {

	protected double x_coordinate;
	protected double y_coordinate;
	protected double xspeed;
	protected double yspeed;
	protected Image img;
	
	public Entity(double x, double y) {
		this.setX_coordinate(x);
		this.setY_coordinate(y);
	}
	
	public abstract void bounce(GraphicsContext gc);
	
	public void draw(GraphicsContext gc) {
		
		gc.clearRect(this.getX_coordinate(), this.getY_coordinate(), this.getImg().getWidth(), this.getImg().getHeight());
		this.setY_coordinate((int)(this.getY_coordinate()+this.getYspeed()));
		this.setX_coordinate((int)(this.getX_coordinate()+this.getXspeed()));
		gc.drawImage(this.getImg(), this.getX_coordinate(), this.getY_coordinate());
	}
	
	public Rectangle2D getBoundary(double width, double height) {
        return new Rectangle2D(getX_coordinate(), getY_coordinate(), width, height);
    }
	 
	public boolean collideWithSide1(Sticks other) {
		return other.getBoundarySide1().intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()));
	}
	
	public String collideWithTopOrButtom1(Sticks other) {
		if(other.getBoundaryTop1().intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()))) {
			return "TOP";
		}else if(other.getBoundaryButtom1().intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()))) {
			return "BUTTOM";
		}
		return "NOT HIT";
		
	}
	
	public boolean collideWithSide2(Sticks other) {
		return other.getBoundarySide2().intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()));
	}
	
	public String collideWithTopOrButtom2(Sticks other) {
		if(other.getBoundaryTop2().intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()))) {
			return "TOP";
		}else if(other.getBoundaryButtom2().intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()))) {
			return "BUTTOM";
		}
		return "NOT HIT";
		
	}
	
	public boolean collidewithWall_X(GraphicsContext gc) {
		Rectangle2D wallTop = new Rectangle2D(0, 5, gc.getCanvas().getWidth(), 5);
		Rectangle2D wallButtom = new Rectangle2D(0, gc.getCanvas().getHeight()-10, gc.getCanvas().getWidth(), 5);
		
		return wallTop.intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight())) ||
				   wallButtom.intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()));
	}
	
	public boolean collidewithWall_Y(GraphicsContext gc) {
		Rectangle2D wallLeft = new Rectangle2D(45, 0, 5, gc.getCanvas().getHeight());
		Rectangle2D wallRight = new Rectangle2D(gc.getCanvas().getWidth()-50, 0, 5, gc.getCanvas().getHeight());
		
		
		return wallRight.intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight())) ||
				   wallLeft.intersects(this.getBoundary(getImg().getWidth(), getImg().getHeight()));
	}
	
	public Image getImg() {
		return img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}


	public double getXspeed() {
		return xspeed;
	}

	public void setXspeed(double xspeed) {
		this.xspeed = xspeed;
	}

	public double getYspeed() {
		return yspeed;
	}

	public void setYspeed(double yspeed) {
		this.yspeed = yspeed;
	}

	public double getX_coordinate() {
		return x_coordinate;
	}
	
	public void setX_coordinate(double x_coordinate) {
		this.x_coordinate = x_coordinate;
	}
	

	public double getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(double y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	
	
}
