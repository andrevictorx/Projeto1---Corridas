import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
	
	// static indica que scanner e lc são variáveis de classe
	// ou seja, não precisa de objeto desta classe para existirem
	private static Scanner scanner = new Scanner(System.in);
	
	// passando a usar a classe ListaCorridas que encapsula sua ED
	private static ListaCorridas lc = new ListaCorridas("Amigos da faculdade"); 

	public static void main(String[] args) {
		int opcao;
		
		popularLista(); // inicialização para testes

		// menu da aplicação
		do {
			System.out.println("\n------- Menu -------\n");
			System.out.println("   0 - sair");
			System.out.println("   1 - cadastrar nova Corrida");
			
			System.out.println("   2 - pesquisar Corrida por nome");
			System.out.println("   3 - remover Corrida");
			
			System.out.println("   4 - ordenar lista AZ");
			System.out.println("   5 - ordenar lista ZA");
			
			System.out.println("   6 - ordenar lista idade descendente");
			
			System.out.println("   7 - gerar lista aniversariantes");
			
			System.out.println("   8 - listar todos Corridas");
			System.out.print("\nOpcao: ");
			opcao = scanner.nextInt(); // deixa um Enter no buffer do teclado
			scanner.nextLine(); // vai consumir o Enter
			switch (opcao) {
				case 1:
					lc.addCorrida(lerCorrida());
					break;
				case 2:
					pesquisarCorrida();
					break;
				case 3:
					removerCorrida();
					break;
				case 4: { 
					lc.sortAZ();
					break;
				}
				case 5: { 
					lc.sortZA();
					break;
				}
				case 6: 
					lc.sortCorridaCronologica();
					break;
					
				case 7: // gerar listagem aniversariantes por mês ("café da firma") 
					// 1 parte - ordenar pela nascimento nos meses
					lc.sortAniversariosAno();
					
					
					// 2 parte - imprimir relatório com os meses
					// para não precisar de vetor de strings, usaremos as
					// funcionalidades da API de localidade e texto
					// https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html
					Locale ptBR = new Locale("pt", "br");
					LocalDate agora = LocalDate.now();
					int ano = agora.getYear();
					
					System.out.printf("\n------- Aniversariantes %d  -------\n", ano);
					
					int mesAnt = 0;
					for (int i=0; i<lc.getSize(); i++) {
						Corrida c = lc.getCorrida(i);
						int mesCorrida = c.getDataCorrida().getMonthValue();
						int diaCorrida = c.getDataCorrida().getDayOfMonth();
						if (mesCorrida != mesAnt) { // cabeçalho do mês
							System.out.println("\n- " + 
						    c.getDataCorrida().getMonth().getDisplayName(TextStyle.FULL, ptBR));
							mesAnt = mesCorrida;
						}
						System.out.printf("   %02d - %s (%d) \n", diaCorrida, c.getNome(), c.getDataCorrida());
					}				
					
					break;
					
				case 8: {
					mostrarLista();
					break;
				}
			}
		} while (opcao != 0);
		scanner.close(); 
	}
	

	private static Corrida lerCorrida() {
		String nome;
		System.out.println("\n------- Nova Corrida -------\n");
		System.out.print("Nome da corrida: ");
		nome = scanner.nextLine();
		System.out.print("Distância da corrida: ");
		 = scanner.nextLine();
		Corrida Corrida = new Corrida(nome);
		return Corrida;
	}
	
	// este método de classe, tem acesso ao arraylist lc, que é um atributo de classe
	// portanto, não foi necessário passar por parâmetro
	private static void mostrarLista() {
		System.out.println("\n------- Lista de Corridas -------\n");
		
		// LOOP estilo 1 -----------------------------------------
		// navegar pelo ArrayList usando i e size, i-éssimo objeto
		// sentimento físico de posição que é o índice
		// --------------------------------------------------------
		for(int i = 0; i < lc.getSize(); i++) {
			System.out.println(lc.getCorrida(i)); // devolve o objeto da i-éssima posição
		}
	}
	
	// para efeitos de teste, uma população inicial da lista
	private static void popularLista() {
		lc.addCorrida(new Corrida("Fulano de Tal", "fulano@gmail.com", 14, 2, 2000));
		lc.addCorrida(new Corrida("Feliciano dos Montes", "astro@hotmail.com", 20, 05, 1970));
		lc.addCorrida(new Corrida ("Zureta dos Campos", "zuzu@outlook.com", 20, 04, 2010));
		lc.addCorrida(new Corrida ("Beltrana dos Lagos", "bel@ufpr.br", 26, 10, 1971));
		
		// apenas para exemplificar as opçoes de sobrecarga do construtor
		//lc.add(new Corrida());
		lc.addCorrida(new Corrida("Cicrano da Montanha", "mont@msn.com", 4, 3, 2020));
		lc.addCorrida(new Corrida("Astrogildo Contente", "feliz@tal.com", 14, 2, 2010));
		lc.addCorrida(new Corrida("Karolynne", "carol@gmail.com", LocalDate.of(2012, 8, 4)));
	}
	
	
	private static void pesquisarCorrida() {
		String nome;
		System.out.println("\n------- Pesquisar Corrida -------\n");
		System.out.print("Qual seu nome: ");
		nome = scanner.nextLine();
		
		// LOOP estilo 2 -----------------------------------------
		// Para Cada Corrida da ListaCorridas
		// for each 
		// funcionado pois a nova classe implements Iterable e possui o método iterator
		// --------------------------------------------------------
		for (Corrida Corrida : lc) {
			//System.out.printf("Comparando %s com %s\n", nome, Corrida.getNome());
			if (Corrida.getNome().toUpperCase().contains(nome.toUpperCase())) {
				System.out.println("Achei: "+ Corrida); // chama toString de modo transparente
			}
		}
	}
	
	private static void removerCorrida() {
		String nome;
		System.out.println("\n------- Remover Corrida -------\n");
		System.out.print("Qual nome a remover da lista: ");
		nome = scanner.nextLine();
		
		// LOOP estilo 3 -----------------------------------------
		// usando while com um ITERATOR
		// olhar o design pattern em https://refactoring.guru/pt-br/design-patterns/iterator 
		// também não se preocupa com a posição
		// vai pedindo até acabar
		// --------------------------------------------------------
		Iterator<Corrida> it = lc.iterator();
		while (it.hasNext()) {
		// enquanto tem alguém na iteração da lista?
			Corrida Corrida = it.next(); // manda o cidadão
			if (Corrida.getNome().toUpperCase().contains(nome.toUpperCase())) {
				System.out.println("Acheio o Corrida: "+ Corrida.getNome());
				System.out.print("Apagar <S/N>: ");
				String r = scanner.nextLine();
				if (r.toUpperCase().charAt(0) == 'S') {
					it.remove();
				}
			}
		}
	}
	
}






