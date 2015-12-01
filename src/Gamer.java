
public class Gamer {
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