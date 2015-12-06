import java.io.Serializable;

public class Gamer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7549041561246543269L;
	private String name;
	private int score;
	private int jumps;
	private double time;
	
	public Gamer(String name, int jumps, int score, double time) {
		this.name = name;
		this.score = score;
		this.jumps = jumps;
		this.time = time;
	}
	
	public Gamer(String name) {
		this.name = name;
		score = 0;
		jumps = 0;
		time = 0;
	}
	
	public void setScore(int s) {
		score = s;
	}
	public void setJumps(int j) {
		jumps = j;
	}
	public void setTime(double t) {
		time = t;
	}
	public void setName(String s) {
		name = s;
	}
	
	public int getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
	public int getJumps() {
		return jumps;
	}
	public double getTime() {
		return time;
	}
}
