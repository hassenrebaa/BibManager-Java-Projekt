package BibRebb;
import java.util.Comparator;

public class EintragComparator implements Comparator<BibEintrag> {

	public int compare(BibEintrag obj1, BibEintrag obj2) {
		// TODO Auto-generated method stub
		return Integer.compare( obj2.getJahr(),obj1.getJahr());
	}

}
