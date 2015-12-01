import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame {
	private JFrame jf = new JFrame();
	private JPanel mainPanel = new JPanel();
	
	private GameWindow gw = new GameWindow();
	
	private JTextField name = new JTextField();
    private JButton jb = new JButton("Confirm");
    private JButton start = new JButton("Start");
    
    //private List<Gamer> highScore = new ArrayList<Gamer>();
    private GamerData gd = new GamerData();
    
    private JTable jt = new JTable(gd);
    
    protected boolean named = false;
    protected boolean played = false;
	
    
	public void initMenu_1() throws IOException {
		
		ActionListener mi_1 = new MyListener();
		start.addActionListener(mi_1);
		start.setActionCommand("start");
		start.setEnabled(false);
		
		JButton load = new JButton("Load");
		ActionListener mi_2 = new MyListener();
		load.addActionListener(mi_2);
		load.setActionCommand("load");
		
		JButton highS = new JButton("Exit/Save");
		ActionListener mi_3 = new MyListener();
		highS.addActionListener(mi_3);
		highS.setActionCommand("exit");
		
		ActionListener al = new MyListener();
		jb.addActionListener(al);
		jb.setActionCommand("confirm");
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setVisible(true);
		
		JPanel buttonPanel = new JPanel();
			JPanel btp1 = new JPanel();
			JPanel btp2 = new JPanel();
			JPanel btp3 = new JPanel();
		JPanel tablePanel = new JPanel();
		JPanel namePanel = new JPanel();
		
		btp1.add(start);
		btp2.add(load);
		btp3.add(highS);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(btp1);
		buttonPanel.add(btp2);
		buttonPanel.add(btp3);

        jt.setFillsViewportHeight(true);
        jt.setEnabled(true);
        jt.setAutoCreateRowSorter(true);
        jt.setGridColor(Color.GRAY);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tablePanel.add(jt);
        
        readHighScore();
        Collections.sort(gd.getData(), new GamerComparator());
        
        
        JLabel nevText = new JLabel("Name: ");
        name.setColumns(20);
        
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(nevText);
        namePanel.add(name);
        namePanel.add(jb);
		
		mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
		
		mainPanel.add(tablePanel, BorderLayout.CENTER);
		
		mainPanel.add(namePanel, BorderLayout.PAGE_END);
		
		jf.add(mainPanel);
		jf.pack();
		jf.setVisible(true);
	}

	public Window(int width, int height) throws IOException{
		JFrame frame = new JFrame("Title");
		jf = frame;
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jf.setSize(width, height);
		initMenu_1();
		
	}
	
	private void readHighScore() throws IOException {
		FileReader fr = new FileReader("highscore.txt");
		BufferedReader br = new BufferedReader(fr);
		while(true) {
			String line = br.readLine();
			if(line == null) break;
			Gamer newG = lineToGamer(line);
			gd.addGamer(newG);
		}
		br.close();
	}
	
	private Gamer lineToGamer(String line) {
		String[] sl = line.split(" ");
		Gamer tmp = new Gamer(sl[0], Integer.parseInt(sl[1]), Integer.parseInt(sl[2]), Double.parseDouble(sl[3]));
		return tmp;
	}

	
	
	public void setGamerStat(int score, int jumps, double time) {
		//if(gd.getGamer(gd.size()).getName() == "Unknown")
		gd.getGamer(gd.size()).setScore(score);
		gd.getGamer(gd.size()).setJumps(jumps);
		gd.getGamer(gd.size()).setTime(time);
	}
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
			case "start" : {
				System.out.println("start"); 
				jf.setVisible(false);
				if(!named) {
					gd.newGamer("Unknown");;
					System.out.println("unknown");
					gw = new GameWindow(jf, "Unknown");
					played = true;
				} else {
					gw = new GameWindow(jf, name.getText());
					played = true;
				}
				break;
			}
			case "load" : System.out.println("load"); break;
			case "exit" : {
				System.out.println("exit");
				if(played) {
					gd.addGamer(new Gamer(gw.getName(), gw.getJumps(), gw.getScore(), gw.getTime()));
					try {
						gd.saveList();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				} else {
					System.exit(0);
				}
				break;
			}
			case "confirm" : {
				System.out.println("confirm"); 
				gd.newGamer(name.getText());
				name.setEnabled(false);
				start.setEnabled(true);
				named = true;
				break;
			}
			}
		}
		
	}
	
}
