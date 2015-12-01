import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;


public class GameWindow implements ComponentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6588925996997490141L;
	private JFrame jf = new JFrame();
	private JFrame old = new JFrame();
	private int frameHeight = 900;
	private int frameWidth = 500;
	GamePanel gp = new GamePanel(frameWidth, frameHeight);
	private JPanel jp = new JPanel(new BorderLayout());
	
	//player STATISTICS
	private String name;
	private int jumps;
	private int score;
	private double time;
	
	public GameWindow() {
		
	}
	
	public GameWindow(JFrame old, String name){
		this.name = name;
		JFrame frame = new JFrame("Bubble Game");
		this.old = old;
		jf = frame;
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		jf.setSize(frameWidth, frameHeight);
		
		JLabel text = new JLabel("Bubble game:");
		text.setEnabled(true);
		
		
		gp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		jp.add(text, BorderLayout.PAGE_START);
		jp.add(gp, BorderLayout.CENTER);
		jf.add(jp);
		gp.addComponentListener(this);
		
		//this.run();
	}
	
	public int getScore() {
		return score;
	}
	public int getJumps() {
		return jumps;
	}
	public double getTime() {
		return time;
	}
	public String getName() {
		return name;
	}



	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		//this.dispose();
		//name = gp.getName();
		jumps = gp.getJumps();
		score = gp.getScore();
		time = gp.getTime();
		jf.dispose();
		old.setEnabled(true);
		old.setVisible(true);
	}



	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
	
