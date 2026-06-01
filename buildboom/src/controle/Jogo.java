package controle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import telas.TelaFase;
import telas.TelaInicial;
import entidades.*;

// A classe jogo é filha da classe JPanel, ou seja, herda os seus métodos e atributos.
public class Jogo extends JPanel implements Runnable {
	// Dimensões
	private int larguraTela = 1000;
	private int alturaTela = 600;
	private int FPS = 45;

	/* A classe Thread é nativa do Java, servindo para executar ações em paralelo,
	como por exemplo renderizar os objetos. */
	Thread gameThread;
	
	TelaInicial tInicial;
	TelaFase tFase;
	
	// Variáveis do controle da Tela
	public int EstadoAtual = 0; // 0 = Menu, 1 = Jogando, 2 = Opções
	public int opçãoSelecionada = -1; // 0 = Jogar, 1 = Opções, 2 = Sair
	public int faseAtual = 1;
	
	public Jogo() {
		this.setPreferredSize(new Dimension(larguraTela, alturaTela));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.tInicial = new TelaInicial(this);
		this.tFase = new TelaFase(this);
		
		// Comandos do mouse no início na tela inicial
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int mx = e.getX();
				int my = e.getY();
				
				if (EstadoAtual == 0) {
					if (mx >= 60 && mx <= 300 && my >= 260 && my <= 310) {
						EstadoAtual = 1; 
					}
					else if (mx >= 60 && mx <= 300 && my >= 360 && my <= 410) {
						EstadoAtual = 2; 
					}
					else if (mx >= 60 && mx <= 300 && my >= 460 && my <= 510) {
						System.exit(0); 
					}
				}	
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int mx = e.getX();
				int my = e.getY();
				
				if (EstadoAtual == 0) {
					if (mx >= 60 && mx <= 300 && my >= 260 && my <= 310) {
						opçãoSelecionada = 0;
					}
					else if (mx >= 60 && mx <= 300 && my >= 360 && my <= 410) {
						opçãoSelecionada = 1; 
					}
					else if (mx >= 60 && mx <= 300 && my >= 460 && my <= 510) {
						opçãoSelecionada = 2; 
					} 
					else {
						opçãoSelecionada = -1; 
					}
				}
			}
		});
		Iniciar();
	}
	
	public int getLarguraTela() {
		return larguraTela;
	}
	
	public int getAlturaTela() {
		return alturaTela;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void Iniciar() {
		// Colocando clientes
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		// Instanciando a sua parte: O gerenciador de dados e componentes
		GerenciadorComponentes gerenciador = new GerenciadorComponentes();
		
		clientes.add(new Cliente (
				"Gamer",
				"Quero um computador para jogos pesados.",
				"Ryzen 3",
				"32GB",
				"750W"
		));
		
		clientes.add(new Cliente (
				"Senhorinha",
				"Quero acessar a internet e ver receitas.",
				"Athlon",
				"8GB",
				"500W"
		));
		
		clientes.add(new Cliente(
				"Editor de Vídeo",
				"Preciso editar e renderizar vídeos.",
				"Ryzen 3",
				"32GB",
				"650W"
		));
		
		clientes.add(new Cliente(
				"Estudante",
				"Preciso estudar e fazer trabalhos.",
				"Intel i3",
				"16GB",
				"500W"
		));
		
		// Sorteio do cliente
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		
		for(int rodada = 1; rodada <= 4; rodada++) {
			int indice = random.nextInt(clientes.size());
			
			Cliente clienteAtual = clientes.get(indice);
			
			System.out.println("\n======================");
			System.out.printf("RODADA: %d\n", rodada);
			System.out.println("======================");
			
			System.out.println("Cliente chegou!");
			System.out.printf("Tipo: %s\n", clienteAtual.getNome());
			System.out.printf("Pedido: %s\n", clienteAtual.getPedido());
			
			clientes.remove(indice);
			
			// Array para salvar os objetos dos componentes escolhidos pelo jogador
			Componente[] escolhas = new Componente[3];
			System.out.println("\nBuildBOOM iniciado!");
			
			// Executa as 3 etapas de escolha (0 = CPU, 1 = RAM, 2 = Fonte)
			for(int i = 0; i < 3; i++) {
				// Sua função puxando a lista de objetos do banco de dados simulado
				List<Componente> opcoesDisponiveis = gerenciador.obterOpcoesParaRodada(i);
				
				System.out.printf("\nEscolha o componente da Etapa %d:\n", i + 1);
				
				for(int j = 0; j < opcoesDisponiveis.size(); j++) {
					System.out.printf("%d - %s\n", j + 1, opcoesDisponiveis.get(j).getNome());
				}
				
				System.out.printf("Digite sua escolha: ");
				int escolha = sc.nextInt();
				
				while(escolha < 1 || escolha > opcoesDisponiveis.size()) {
					System.out.printf("Escolha inválida! Digite novamente: ");
					escolha = sc.nextInt();
				}
				
				// Salva o objeto escolhido
				escolhas[i] = opcoesDisponiveis.get(escolha - 1);
			}
			
			System.out.println("\n=================================");
			System.out.println("   ESCOLHAS DO JOGADOR (OBJETOS) ");
			System.out.println("=================================");
			System.out.printf("Processador: %s [Socket: %s | Freq: %dMHz]\n", escolhas[0].getNome(), escolhas[0].getSocket(), escolhas[0].getFrequencia());
			System.out.printf("Memória RAM: %s [Frequência: %dMHz]\n", escolhas[1].getNome(), escolhas[1].getFrequencia());
			System.out.printf("Fonte: %s [Potência: %dW]\n", escolhas[2].getNome(), escolhas[2].getPotencia());
			
			// Executa a sua regra técnica de validação de compatibilidade
			boolean valido = gerenciador.validarCompatibilidade(escolhas[0], escolhas[1], escolhas[2]);
			System.out.printf("Resultado Técnico da Montagem: %s\n", (valido ? "COMPATÍVEL! ✔️" : "INCOMPATÍVEL! ❌"));
			System.out.println("=================================");
		}
		System.out.println("\nFim de jogo!");
		sc.close();
	}
	
	@Override
	public void run() {
		double IntervaloDeDesenho = 1000000000 / FPS;
		double quandoDesenharOProximoobjeto = System.nanoTime() + IntervaloDeDesenho;
		while(gameThread != null) {
			atualizar();
			repaint();
			try {
				double tempoRestante = quandoDesenharOProximoobjeto - System.nanoTime();
				tempoRestante = tempoRestante / 1000000;
				if(tempoRestante < 0) {
					tempoRestante = 0;
				}
				Thread.sleep((long)tempoRestante);
				quandoDesenharOProximoobjeto += IntervaloDeDesenho;
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizar() {
		
	}
	
	// O método paintComponent é do próprio java Swing.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Parte do código que muda a tela de acordo com a opção
		if (EstadoAtual == 0 ) {
			// Menu inicial
			tInicial.desenhar(g2);
		}
		if (EstadoAtual == 1 ) {
			tFase.desenhar(g2);
		}
		if (EstadoAtual == 2 ) {
			// Sair
		}
	}
}
