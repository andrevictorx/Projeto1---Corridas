import java.time.LocalDate;
import java.time.Period;

// implements indica quais INTERFACES esta classe implementa
// ela pode implementar interfaces da API, como é o caso aqui
// ou interfaces criadas pelo programados
public class Corrida implements Comparable<Corrida>{
	// este implements obriga a REDEFINICAO do método compareTo

	// atributos da classe >> ESTADO de um objeto
	// nome é uma variável de instância privada
	private String nome;
	private LocalDate dataCorrida;
	private int distancia;
	private int tempo;
	
	// o private garante o ENCAPSULAMENTO da classe

	// métodos >> COMPORTAMENTO da classe 
	// exemplo de sobrecarga ou overload
	// o nome do método é o MESMO
	// mas a ASSINATURA (lista de parâmetros) é DIFERENTE
	// neste exemplo atual temos 5 OPCOES de construtor
	public Corrida() {
		this("Sem nome", LocalDate.of(1800, 1, 1), 0, 0);
		// this. separa o nome do atributo do nome do parâmetro (iguais)
		// this() aciona o contrutor desta classe
	}
	
	public Corrida(String nome) {
		this(nome, LocalDate.of(1800, 1, 1), 0, 0);
		// data default para representar sem data
		// decisao de projeto, 1/1/1800 significa sem data cadastrada
		// poderia não atribuir, ficaria NULL, 
		// em ambos os casos cuidar quando for tratar a data que
		// ficou opcional a data....
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

	// setters e getters, são a interface pública da classe
	// é através deles que outras classes interagem com esta
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
		// regras de negócio
		// que testam o valores dos parâmetro
		// se ok, toca, senão lança uma exceção
		this.distancia = distancia;
	}
	
	public int getTempo() {
		// tempo transcorrido entre a data de nascimento e hoje
		return Period.between(dataCorrida, LocalDate.now()).getYears();
	}
	
	public LocalDate getDataCorrida() {
		return dataCorrida;
	}
	
	// exemplo de setter com sobrecarga
	public void setDataCorrida(LocalDate dataCorrida) {
		// normalmente em um setter se avaliam regras de negocio
		// antes de atribuir
		this.dataCorrida = dataCorrida;
	}
	
	public void setDataCorrida(int ano, int mes, int dia) {
		this.dataCorrida = LocalDate.of(ano, mes, dia);
	}

	@Override
	// compareTO compara os dados de this (este objeto) com o que veio no parâmetro
	public int compareTo(Corrida c) {
		if ((this.distancia - c.distancia) != 0)
			return this.distancia - c.distancia;
		return this.nome.compareTo(c.nome.compareToIgnoreCase(nome))
		
	}
	// o compareTo implementa a comparação do atributo escolhido
	// como sendo a ORDEM NATURAL para objetos desta classe
	// esta opção feita pelo programador influencia o uso do sort,
	// método da API disponível no framework Collections

	
	// exemplo de REDEFINICAO ou SOBRESCRITA ou OVERRIDE
	// ocorre quando subclasse implementa nova versão de método herdado da superclasse
	// mesmo NOME e mesma ASSINATURA
	@Override // decorator
	public String toString() {
		String string = "";
		// teste da data de nascimento default
		if (dataCorrida.getYear() != 1800) // poderia ser com null, caso atributo vazio
			return string += dataCorrida + " - " +  nome + " - " + distancia + " m - " + tempo + " minutos"; 
		return string += "Sem data cadastrada" + " - " +  nome + " - " + distancia + "m - " + tempo + " minutos";
	}


	
}
