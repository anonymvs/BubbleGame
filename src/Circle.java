
import java.awt.geom.Point2D;
import java.lang.Math;
import java.util.List;
//import java.util.Random;

public class Circle  {
	protected double x;
	protected double y;
	protected double r;
	
	public Circle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public Circle() {
	}
	
	public boolean isOverlapped(Platform c) {
		double d = Math.sqrt((this.x-c.x)*(this.x-c.x) + (this.y-c.y)*(this.y-c.y));
		if(d < this.r + c.r + 20) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOverlappedList(List<Platform> l) {
		boolean ans = false;
		for(int i = 0; i < l.size(); i++) {
			if(this.isOverlapped(l.get(i))) ans = true;
		}
		return ans;
	}
	
	public double getX() {
		return x-r;
	}
	
	public double getY() {
		return y-r;
	}
	
	public double getWidth() {
		return 2*r;
	}
	
	public double getHeight() {
		return 2*r;
	}
	
	public double getR() {
		return r;
	}
	
	public Point2D getK() {
		return new Point2D.Double(x, y);
	}
	
	public void setX(double x) {
		this.x = x+r;
	}
	
	public void setY(double y) {
		this.y = y+r;
	}
}
