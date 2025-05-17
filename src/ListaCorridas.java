import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListaCorridas implements Iterable<Corrida>{
	private String nomeLista;
	private ArrayList<Corrida> lc; // encapsular o ArrayLists
	// nesta nova classe, escondendo a estrutura de dados da
	// parte de controle da aplicação
	
	public ListaCorridas(String nomeLista) {
		super();
		this.nomeLista = nomeLista;
		lc = new ArrayList<Corrida>();
	}
	
	// criando método para interfacear com a lista
	// como é um arraylist, basicamente estamos usando 
	// os métodos já disponíveis
	
	// caso fosse um Estrutura de Dados proprietário
	// cada método demandaria o algorimto respectivo
	public Corrida getCorrida(int i) {
		return lc.get(i);
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
	
	public int getSize() {
		return lc.size();
	}
	
	
	public void sortCorridaCronologica() {
		 // ordenar lista idade descendente
		// usaremos uma classe anônima para criar o comparator
		// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
		Collections.sort(lc, new Comparator<Corrida>() {

			@Override // a interface Comparator exige o método compare
			// https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
			public int compare(Corrida c1, Corrida c2) {
				return c1.getDataCorrida().compareTo(c2.getDataCorrida());
			}
			
		});
	}
	
	public void sortDistancia() {
		// ordenar pela nascimento nos meses
		// usamos getDayOfYear (1..365) que incorpora dia e mês
		Collections.sort(lc, new Comparator<Corrida>() {
			// usamos interface Comparator que exige método compare
			// e classe anônima (uso específico, uma vez etc)
			@Override
			public int compare(Corrida c1, Corrida c2) {
				return c1.getDistancia().compareTo(c2.getDistancia());
			}
			
		});
	}

	@Override // para implementar os métodos exigidos pela interface
	// no caso da Iterable, obrigatório fazer iterator
	// no da Comparable, fazer compareTo
	// no da Comparator, fazer o compare
	public Iterator<Corrida> iterator() {
		return lc.iterator();
	}

	@Override // recomendação, sempre implementar toString
	public String toString() {
		return "ListaCorridas [nomeLista=" + nomeLista + ", lc=" + lc + "]";
	}
	
	
	
}
