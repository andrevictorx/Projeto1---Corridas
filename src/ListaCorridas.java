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
	
	public void sortAZ() {
		// ordem natural de nome A..Z
		// o sort, método static da Collections,
		// chama o compareTo da Corrida, por isso ela sabe como comparar
		// dois objetos do classe Corrida, e assim ordena o ArrayList
		Collections.sort(lc);
	}
	
	public void sortZA() {
		// reverseorder é um comparator
		// que retorna o contrario da ordem natural, portanto de Z..A
		Collections.sort(lc, Collections.reverseOrder());
		// o sort com este comparator, fara de Z..a
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
	
	public void sortAniversariosAno() {
		// ordenar pela nascimento nos meses
		// usamos getDayOfYear (1..365) que incorpora dia e mês
		Collections.sort(lc, new Comparator<Corrida>() {
			// usamos interface Comparator que exige método compare
			// e classe anônima (uso específico, uma vez etc)
			@Override
			public int compare(Corrida c1, Corrida c2) {
				int d1 = c1.getDataCorrida().getDayOfYear();
				int d2 = c2.getDataCorrida().getDayOfYear();
				if (d1 != d2) // quando mesmo dia, ordem alfabética
					return Integer.compare(d1, d2);
				// ver sobre wrappers
				// https://www.w3schools.com/java/java_wrapper_classes.asp
				// https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html
				else
					return c1.compareTo(c2); // vem lá da Corrida
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
