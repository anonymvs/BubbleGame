import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	//class attributes
    private GamerData gd = new GamerData();
    protected boolean named = false;
    protected boolean played = false;
	//frame attributes
	private JFrame jf = new JFrame();
	private JPanel mainPanel = new JPanel();
	private JTextField name = new JTextField();
    private JButton jb = new JButton("Confirm");
    private JButton start = new JButton("Start");
    private JTable jt = new JTable(gd);
    //new frame
    private GameWindow gw = new GameWindow();
    private Gamer actualGamer = new Gamer("Unknown");
	
    
    public Window(int width, int height){
		JFrame frame = new JFrame("Title");
		jf = frame;
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jf.setSize(width, height);
		
		initMenu_1();	
	}
    
	public void initMenu_1(){
		
		ActionListener mi_1 = new MyListener();
		start.addActionListener(mi_1);
		start.setActionCommand("start");
		start.setEnabled(false);
		
		
		JButton load = new JButton("Load");
		ActionListener mi_2 = new MyListener();
		load.addActionListener(mi_2);
		load.setActionCommand("load");
		load.setEnabled(false);
		
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
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
			case "start" : {
				System.out.println("start"); 
				jf.setVisible(false);
				if(!named) {
					System.out.println("unknown");
					gw = new GameWindow(jf, actualGamer);
					played = true;
				} else {
					gw = new GameWindow(jf, actualGamer);
					played = true;
				}
				break;
			}
			case "load" : System.out.println("load"); break;
			case "exit" : {
				System.out.println("exit");
				if(played) {
					actualGamer = gw.getGamer();
					//System.out.println(actualGamer);
					gd.addGamer(actualGamer);
					
					try {
						gd.saveList();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i = 0; i < gd.size(); i++) {	
						System.out.print(gd.getGamer(i).getName());
						System.out.print(gd.getGamer(i).getScore());
						System.out.print(gd.getGamer(i).getJumps());
						System.out.print(gd.getGamer(i).getTime());
					}
					System.exit(0);
				} else {
					System.exit(0);
				}
				break;
			}
			case "confirm" : {
				System.out.println("confirm"); 
				actualGamer.setName(name.getText());
				name.setEnabled(false);
				start.setEnabled(true);
				named = true;
				break;
			}
			}
		}
		
	}
	
}
