package teorijske4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RangLista {
	private List<Igra> lista = new ArrayList<>();
	
	public synchronized int dodaj(Igra igra) {
		lista.add(igra);
		lista.sort(Comparator.comparing(Igra::getBrPokusaja));
		int rang = 1 + lista.indexOf(igra);
		if(lista.size() > 10) {
			lista.remove(10);
		}
		return rang;
	}
}
