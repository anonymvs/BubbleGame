
import java.lang.Math;
import java.util.List;
import java.util.Random;

public class Player extends Circle{
	private Platform pl = new Platform();
	// in degree
	private double alpha;
	private double velP = 1;
	private double velFall = 0.02;
	private Direction v = Direction.RIGHT;
	private double[] vector = {0,0};
	private State state = State.ALIVE;
	private int jumps = 0;
	
	public enum Direction {
		LEFT, RIGHT, JUMP
	}
	
	public enum State {
		ALIVE, TOO_SLOW, TOO_FAST, OUT_OF_RANGE
	}
	
	public Player() {
		x = 0;
		y = 0;
		r = 10;
	}
	
	public String identity() {
		return "Player";
	}
	
	public State testBorders(int width, int height) {
		if(x + r > width || x - r < 0){
			state = State.OUT_OF_RANGE;
			return state;
		}
		if(y - r < 0) {
			state = State.TOO_FAST;
			return state;
		}
		if(y + r > height) {
			state = State.TOO_SLOW;
			return state;
		}
		return state;
	}
	
	public void givePlatform(Platform p) {
		pl = p;
		Random rand = new Random();
		alpha = rand.nextDouble()*360;
		positionUpdate(pl);
	}
	
	public void positionUpdate(Platform p) {
		pl = p;
		x = pl.x + (pl.r+r) * Math.cos(Math.toRadians(alpha));
		y = pl.y + (pl.r+r) * Math.sin(Math.toRadians(alpha));
		vector[0] = x - pl.x;
		vector[1] = y - pl.y;
	}
	
	public Platform platformViewer(List<Platform> l) {
		for(int i = 0; i < l.size(); i++) {
			if(pl != l.get(i)) {
				if(isOrbital(l.get(i))) {
					pl = l.get(i);
					if((Math.abs((x - pl.x) / (pl.r + r))) > 1) {
						if(((x - pl.x) / (pl.r + r)) < -1) alpha = Math.toDegrees(Math.acos(-1));
						if(((x - pl.x) / (pl.r + r)) < 1) alpha = Math.toDegrees(Math.acos(1));
					} else {
						alpha = Math.toDegrees(Math.acos((x - pl.x) / (pl.r + r )));
					}
					System.out.println("alpha: " + alpha);
					v = Direction.RIGHT;
					jumps++;
				}
			}		
		}	
		return pl;
	}
	
	public boolean isOrbital(Platform p) {
		double d = Math.sqrt((this.x-p.x)*(this.x-p.x) + (this.y-p.y)*(this.y-p.y));
		if(d < (this.r + p.r + 1) && d > (this.r + p.r - 1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveAround(Platform p) {
		switch (v) {
		case RIGHT : {
			alpha-= velP;
			positionUpdate(p);
			break;
		}
		case LEFT : {
			alpha+= velP;
			positionUpdate(p);
			break;
		}
		case JUMP : {
			x += velFall * vector[0];
			y += velFall * vector[1];
			break;
		}
		default: {
			v = Direction.RIGHT;
			alpha+= velP;
			positionUpdate(p);
			break;
		}	 
		}
	}
	
	public void setDir(Direction d) {
		v = d;
	}
	
	public Direction getDir() {
		return v;
	}
	
	public int getJumps() {
		return jumps;
	}
}
