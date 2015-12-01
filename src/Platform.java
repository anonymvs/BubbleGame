import java.util.Random;

public class Platform extends Circle{
	public Platform() {
		Random randX = new Random();
		Random randY = new Random();
		Random randR = new Random();
		r = randR.nextInt(50) + 30;
		x = randX.nextInt(350) + r;
		y = randY.nextInt(300) + r;
	}
	
	public Platform(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public String identity() {
		return "Platform";
	}
}
