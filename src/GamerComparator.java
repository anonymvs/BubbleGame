
import java.util.Comparator;

public class GamerComparator implements Comparator<Gamer> {

	@Override
	public int compare(Gamer arg0, Gamer arg1) {
		if(arg0.getScore() < arg1.getScore()) return 1;
		if(arg0.getScore() == arg1.getScore()) return 0;
		if(arg0.getScore() > arg1.getScore())return -1;
		else return 0;
	}

}
