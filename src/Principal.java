import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
			System.out.println("   2 - listar corridas em ordem cronológica");
			System.out.println("   3 - procurar Corrida por nome");
			System.out.println("   4 - ordenar corridas por distância");
			System.out.println("   5 - mostrar resumo de desempenho");
			System.out.println("   6 - remover Corrida");			
			System.out.print("\nOpcao: ");

			opcao = scanner.nextInt(); // deixa um Enter no buffer do teclado
			scanner.nextLine(); // vai consumir o Enter
			switch (opcao) {
				case 1:
					lc.addCorrida(novaCorrida());
					break;
				case 2:
					lc.sortCorridaCronologica();
					mostrarLista();
					break;
				case 3:
					pesquisarCorrida();
					break;
					
				case 4: {
					lc.sortDistancia();
					mostrarLista();
					break;
				}
				case 5: { 
					resumoDesempenho();
					break;
				}
				case 6: 
					removerCorrida();
					break;
			}
		} while (opcao != 0);
		scanner.close(); 
	}
	

	private static Corrida novaCorrida() {
		String nome;
		String dataCorrida;
		int distancia;
		int tempo;
		System.out.println("\n------- Nova Corrida -------\n");
		System.out.print("Digite o nome da corrida: ");
		nome = scanner.nextLine();
		System.out.print("Digite a data da corrida (dd/MM/yyyy): ");
		dataCorrida = scanner.nextLine();
		System.out.print("Digite a distância da corrida (em metros): ");
		distancia = scanner.nextInt();
		scanner.nextLine(); // consome o Enter
		System.out.print("Digite o tempo da corrida (em minutos): ");
		tempo = scanner.nextInt();
		scanner.nextLine(); // consome o Enter
		Corrida Corrida = new Corrida(nome,
				LocalDate.parse(dataCorrida, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				distancia, tempo);
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

	private static void resumoDesempenho() {
		int quantidade_corridas = lc.getSize();
		int distancia_total = 0; // em metros
		int tempo_total = 0;     // em minutos
		int tempo_medio_por_corrida = 0;
		float pace_medio;

		for(int i = 0; i < lc.getSize(); i++) {
			distancia_total += lc.getCorrida(i).getDistancia();
			tempo_total += lc.getCorrida(i).getTempo();
		}

		if (quantidade_corridas > 0 && distancia_total > 0) {
			tempo_medio_por_corrida = tempo_total / quantidade_corridas;

			// calculando pace em min/km
			pace_medio = (float) tempo_total / (distancia_total / 1000.0f);

			// conversão do pace para minutos e segundos
			int pace_minutos = (int) pace_medio;
			int pace_segundos = (int) ((pace_medio - pace_minutos) * 60);

			System.out.println("Resumo de desempenho");
			System.out.println("Número de corridas: " + quantidade_corridas);
			System.out.println("Distância total percorrida: " + distancia_total + " m");
			System.out.println("Tempo total gasto: " + tempo_total + " minutos");
			System.out.println("Tempo médio por corrida: " + tempo_medio_por_corrida + " minutos");
			System.out.printf("Pace médio: %02d:%02d min/km\n", pace_minutos, pace_segundos);
		} else {
			System.out.println("Não é possível calcular o pace médio (corridas ou distância zeradas).");
		}
	}

	// para efeitos de teste, uma população inicial da lista
	private static void popularLista() {
		lc.addCorrida(new Corrida("Corrida de São Silvestre",13, 5, 2000,15000,90));
		lc.addCorrida(new Corrida("Maratona de Curitiba", 28, 2, 2007,5000,90));
		lc.addCorrida(new Corrida ("Zureta dos Campos", 5, 2, 2007,15000,90));
		lc.addCorrida(new Corrida ("Beltrana dos Lagos", 28, 2, 2025,15000,90));
		lc.addCorrida(new Corrida());
		lc.addCorrida(new Corrida("Maratona de São Paulo", LocalDate.of(2023, 10, 1),21000,200));

	}
	
	
	private static void pesquisarCorrida() {
		String nome;
		System.out.println("\n------- Pesquisar Corrida -------\n");
		System.out.print("Nome da corrida: ");
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
				System.out.println("Achei a Corrida: "+ Corrida.getNome());
				System.out.print("Apagar <S/N>: ");
				String r = scanner.nextLine();
				if (r.toUpperCase().charAt(0) == 'S') {
					it.remove();
				}
			}
		}
	}
	
}






