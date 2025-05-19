import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListaCorridas implements Iterable<Corrida>{
	private String nomeLista;
	private ArrayList<Corrida> lc; 
	
	public ListaCorridas(String nomeLista) {
		super();
		this.nomeLista = nomeLista;
		lc = new ArrayList<Corrida>();
	}

	public Corrida getCorrida(int i) {
		return lc.get(i);
	}

	public int getSize() {
		return lc.size();
	}
	
	public void addCorrida(Corrida Corrida) {
		lc.add(Corrida);
	}
	
	public void removeCorrida(int i) {
		lc.remove(i);
	}
	
	public void removeCorrida(Corrida Corrida) {
		lc.remove(Corrida);
	}
	
	
	public void sortCorridaCronologica() {
		Collections.sort(lc);
	}
	
	public void sortDistancia() {
		Collections.sort(lc, new Comparator<Corrida>() {
			@Override
			public int compare(Corrida c1, Corrida c2) {
				if ((c1.getDistancia() - c2.getDistancia()) != 0)
					return c1.getDistancia() - c2.getDistancia();
				return c1.getNome().compareToIgnoreCase(c2.getNome());
			}
			
		});
	}

	@Override 
	public Iterator<Corrida> iterator() {
		return lc.iterator();
	}

	@Override // recomendação, sempre implementar toString
	public String toString() {
		return "ListaCorridas [nomeLista=" + nomeLista + ", lc=" + lc + "]";
	}
	
}
