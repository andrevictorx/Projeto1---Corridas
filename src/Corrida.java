import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Corrida implements Comparable<Corrida>{

	private String nome;
	private LocalDate dataCorrida;
	private int distancia;
	private int tempo;
	

	public Corrida() {
		this("Sem nome", LocalDate.of(1800, 1, 1), 0, 0);

	}
	
	public Corrida(String nome) {
		this(nome, LocalDate.of(1800, 1, 1), 0, 0);

	}
	
	public Corrida(String nome, LocalDate dataCorrida){
		this(nome, dataCorrida, 0, 0); 
	}
	
	public Corrida(String nome, int dia, int mes, int ano) {
		this(nome, LocalDate.of(ano, mes, dia), 0, 0);
	}
	
	public Corrida(String nome, LocalDate dataCorrida, int distancia, int tempo) {
		this.nome = nome;
		this.dataCorrida = dataCorrida;
		this.distancia = distancia;
		this.tempo = tempo;
	}
	public Corrida(String nome, int dia, int mes, int ano, int distancia, int tempo) {
		this.nome = nome;
		this.dataCorrida = LocalDate.of(ano, mes, dia);
		this.distancia = distancia;
		this.tempo = tempo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String n) {
		nome = n;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) { 
		if (distancia > 0) {
			System.out.println("Distância não pode ser negativa");
			return;
		}
		this.distancia = distancia;
	}
	
	public int getTempo() {
		return this.tempo;
	}

	public void setTempo(int tempo) {
		if (tempo < 0) {
			System.out.println("Tempo não pode ser negativo");
		}
		this.tempo = tempo;
	}
	
	public LocalDate getDataCorrida() {
		return dataCorrida;
	}
	
	public void setDataCorrida(LocalDate dataCorrida) {
		if (dataCorrida == null) {
			System.out.println("Data não pode ser nula");
			return;
		}
		if (dataCorrida.getYear() < 1900) {
			System.out.println("Data não pode ser menor que 1900");
			return;
		}
		this.dataCorrida = dataCorrida;
	}
	
	public void setDataCorrida(int ano, int mes, int dia) {
		this.dataCorrida = LocalDate.of(ano, mes, dia);
	}

	@Override
	public int compareTo(Corrida c) {
		return this.dataCorrida.compareTo(c.getDataCorrida());
	}

	@Override // decorator
	public String toString() {
		String string = "";
		String data = dataCorrida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		if (dataCorrida.getYear() != 1800)
			return string +=  data + " - " +  nome + " - " + distancia + " m - " + tempo + " minutos"; 
		return string += "Sem data cadastrada" + " - " +  nome + " - " + distancia + "m - " + tempo + " minutos";
	}


	
}
