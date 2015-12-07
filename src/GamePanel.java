
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

class GamePanel extends JPanel implements Runnable, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6516690112791976204L;
	
	protected int score;
	protected long startT = 0;
	protected long stopT = 0;
	protected double seconds = 0;
	//private Timer t = new Timer(10, this);
	double velY = 0.9;
	private int panelHeight;
	//list of circles
	protected List<Platform> cl = new ArrayList<Platform>();
	//player
	private Gamer actualGamer;
	protected Player pl = new Player();
	protected Platform pf = new Platform();
	
	public GamePanel(int width, int height) {
		panelHeight = height;
		cl.add(new Platform(250, 100, 50));
		pl.givePlatform(cl.get(0));
		pf = cl.get(0);
		startT = System.currentTimeMillis();
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	
	public void setGamer(Gamer gamer) {
		actualGamer = gamer;
	}
	public Gamer getGamer() {
		return actualGamer;
	}
	
	//paints out the graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		drawMap((Graphics2D) g);
		this.run();
	}
	
	//draws out the circles
	public void drawMap(Graphics2D g ) {
		for(int i = 0; i < cl.size(); i++) {
			g.setColor(Color.BLACK);
			g.fill(new Ellipse2D.Double(cl.get(i).getX(), cl.get(i).getY(),
					cl.get(i).getWidth(), cl.get(i).getWidth()));
		}
		
		Ellipse2D e = new Ellipse2D.Double(pl.getX(), pl.getY(), pl.getWidth(), pl.getWidth());
		g.setColor(Color.BLUE);
		g.fill(e);
		
		drawStatistics(g);
		
	}
	
	public void drawStatistics(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(5, 5, 90, 65);
		g.setColor(Color.BLACK);
		g.drawRect(5, 5, 90, 65);
		g.setColor(Color.BLUE);
		g.drawString("Score: " + Integer.toString(score), 10, 20);
		g.drawString("Jumps: " + pl.getJumps(), 9, 40);
		g.drawString("Time: " + calcSecs(), 10, 60);
	}
	
	public void setPlatformList() {
		boolean loop = true;
		Platform c = new Platform();
		cl.add(c);
		int n = 0;
		while(loop) {
			Platform tmpC = new Platform();
			if(!tmpC.isOverlappedList(cl)){
				cl.add(tmpC);
			}
			if(cl.size() == 20) {
				loop = false;
			}
			if(n > 1000) loop = false;
			n++;
		}
	}
	
	public void platformHandler() {
		boolean b = true;
		for(int i = 0; i < cl.size(); i++) {
			cl.get(i).setY(cl.get(i).getY() + velY);
			if((cl.get(i).getY() - cl.get(i).getR()) >= panelHeight){
				cl.remove(i);
			}
			if(cl.get(i).getY() < 0){
				b = false;
			}
		}
		
		if (b) {
			Random randX = new Random();
			Random randR = new Random();
			int r = randR.nextInt(50) + 30;
			int x = randX.nextInt(350) + r;
			Platform tmp = new Platform(x, -50, r);
			if(!tmp.isOverlappedList(cl)) {
				cl.add(tmp);
			}
		}
	}

	@Override
	public void run() {
			synchronized (cl) {
				platformHandler();
				
				if(pl.getDir() == Player.Direction.JUMP) {
					pf = pl.platformViewer(cl);
					pl.moveAround(pf);
				} else {
					pl.moveAround(pf);
				}
				
				testPlayer(pl);
				
				try {
					cl.wait(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			pl.setDir(Player.Direction.JUMP);
			//System.out.println("ugrik");
		}
		if(pl.getDir() != Player.Direction.JUMP) {
			if(e.getKeyCode() == 39) {
				pl.setDir(Player.Direction.LEFT);
				//System.out.println("bal");
			}
			if(e.getKeyCode() == 37) {
				pl.setDir(Player.Direction.RIGHT);
				//System.out.println("jobb");
			}
		}
		//System.out.println("megnyomtam");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	private void testPlayer(Player pl) {
		Player.State s = pl.testBorders(500, 900);
		switch (s) {
		case ALIVE : {
			score++;
			break;
		}
		case TOO_SLOW : {
			gameOver();
		}
		case TOO_FAST : {
			gameOver();
		}
		case OUT_OF_RANGE : {
			gameOver();
		}
		}
	}

	private void gameOver() {
		stopT = System.currentTimeMillis();
		long deltaT = stopT - startT;
		seconds = deltaT / 1000.0;
		actualGamer.setJumps(pl.getJumps());
		actualGamer.setScore(score);
		actualGamer.setTime(seconds);
		this.setVisible(false);
	}
	
	public double calcSecs() {
		long tmp = System.currentTimeMillis();
		long deltaTMP = tmp - startT;
		double secs = deltaTMP / 1000.0;
		return secs;
	}
}

