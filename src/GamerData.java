import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;



public class GamerData	extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4056938658884208250L;
	
	List<Gamer> gl = new ArrayList<Gamer>();
	
	public GamerData() {
		
	}
	
	public List<Gamer> getData() {
		return gl;
	}
	
	public Gamer getGamer(int index) {
		return gl.get(index);
	}
	
	public int size() {
		return gl.size();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(rowIndex == 0) {
			return getColumnName(columnIndex);
		} else {
			Gamer gamer = gl.get(rowIndex-1);
			switch(columnIndex) {
			case 0: return rowIndex;
			case 1: return gamer.getName();
			case 2: return gamer.getJumps();
			case 3: return gamer.getScore();
			case 4: return gamer.getTime();
			default: return 0;
		}
		}
	}
	
	public String getColumnName(int index) {
		switch(index) {
		case 0: return "Number";
		case 1: return "Name";
		case 2: return "Jumps";
		case 3: return "Score";
		case 4: return "Time";
		default: return null;
		}
	}
	
	public void addGamer(Gamer gamer) {
		if(!gamerCheck(gamer)) gl.add(gamer);
		//gl.sort(new GamerComparator());
		System.out.println(gamer.getName());
		fireTableDataChanged();
	}
	
	public void newGamer(String s) {
		gl.add(0, new Gamer(s,0,0,0));
		//gl.sort(new GamerComparator());
		fireTableDataChanged();
	}
	
	public boolean gamerCheck(Gamer g) {
		boolean b = false;
		for(int i = 0; i < gl.size(); i++) {
			if(gl.get(i).getName().equals(g.getName())) {
				b = true;
				if(gl.get(i).getScore() < g.getScore()) {
					gl.get(i).setScore(g.getScore());
					gl.get(i).setJumps(g.getJumps());
					gl.get(i).setTime(g.getTime());
				}
			}
		}
		return b;
	}
	
	public void saveList() throws IOException {
		FileWriter fw = new FileWriter("highscore.txt");
		PrintWriter pw = new PrintWriter(fw);
		for(int i = 1; i < gl.size(); i++) {
				pw.println(gl.get(i).getName() + " " +
						   gl.get(i).getJumps() + " " + 
						   gl.get(i).getScore() + " " +
						   gl.get(i).getTime());
				System.out.println("in progress");
			
		}
		System.out.println("kimentve");
		pw.close();
	}
}
