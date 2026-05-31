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

//A classe jogo é filha da classe JPanel, ou seja, herda os seus métodos e atributos.
public class Jogo extends JPanel implements Runnable {
	//Dimensões
	private int larguraTela=1000;
	private int alturaTela=600;
	private int FPS=45;

	
	
/*A classe Thread é nativa do Java, servindo para executar ações em paralelo,
como por exemplo renderizar os objetos.*/
	Thread gameThread;
	
	TelaInicial tInicial;
	TelaFase tFase;
	// Variaveis do controle da Tela
	public int EstadoAtual = 0; // 0 = Menu, 1 = Jogando, 2 = Opções
	public int opçãoSelecionada = -1; // 0 = Jogar, 1 = Opções, 2 = Sair
	public int faseAtual=1;
	
	public Jogo() {
		this.setPreferredSize(new Dimension(larguraTela,alturaTela));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.tInicial= new TelaInicial(this);
		this.tFase= new TelaFase(this);
		
		//Comandos do mouse no incio na tela inicial
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
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	
	public void Iniciar() {
		// colocando clientes
		ArrayList<Cliente> clientes = new ArrayList<>();
		
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
		
		// sorteio do cliente
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		
		for(int rodada = 1; rodada <= 4; rodada++) {
			int indice = random.nextInt(clientes.size());
			
			Cliente clienteAtual = clientes.get(indice);
			
			System.out.println("\n======================");
			System.out.printf("\nRODADA: %d\n", rodada);
			System.out.println("======================");
			
			System.out.println("Cliente chegou!");
			System.out.printf("\nTipo: %s", clienteAtual.getNome());
			System.out.printf("\nPedido: %s", clienteAtual.getPedido());
			
			clientes.remove(indice);
			
			// criando alguns tipos de componentes para teste
			String[] componentes = {
					"Processador",
					"Memória RAM",
					"Fonte"
			};
			
			String[][] opcoes = { 
					// opções quaisquer para teste
					{
						"Ryzen 3",	
						"Intel i3",
						"Athlon"
					},
					{
						"8GB", 
						"16GB",
						"32GB"
					},
					{
						"500W",
						"650W",
						"750W"
					}
			};
			
			String[] escolhas = new String[3];
			System.out.println("BuildBOOM iniciado!");
			
			for(int i = 0; i < 3; i++) {
				System.out.printf("Rodada %d\n", i + 1);
				
				for(int j = 0; j < 3; j++) {
					System.out.printf("%d - %s\n", j + 1, opcoes[i][j]);
				}
				System.out.printf("\nDigite sua escolha da rodada %d: ", i + 1);
				int escolha = sc.nextInt();
				
				while(escolha < 1 || escolha > 3) {
					System.out.printf("\nDigite sua escolha novamente da rodada %d: ", i + 1);
					escolha = sc.nextInt();
				}
				
				escolhas[i] = opcoes[i][escolha - 1];
			}
			
			System.out.println("\nEscolhas do jogador");
			
			for(int i = 0; i < 3; i++) {
				System.out.printf("Rodada %d: opcao %s\n", i + 1, escolhas[i]);
			}
			
			System.out.println("Fim de jogo!");
		}
		sc.close();
	}
	@Override
	public void run() {
		double IntervaloDeDesenho=1000000000/FPS;
		double quandoDesenharOProximoobjeto=System.nanoTime()+IntervaloDeDesenho;
		while(gameThread!=null) {
			atualizar();
			repaint();
			try {
				double tempoRestante=quandoDesenharOProximoobjeto-System.nanoTime();
				tempoRestante=tempoRestante/1000000;
				if(tempoRestante<0) {
					tempoRestante=0;
				}
				Thread.sleep((long)tempoRestante);
				quandoDesenharOProximoobjeto+=IntervaloDeDesenho;
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void atualizar() {
		
	}
	//O método paintComponent é do proprio java Swing.
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		
		//Parte do codigo que muda a tela de acordo com a opção
		
		if (EstadoAtual == 0 ) {
			//Menu inicial
			tInicial.desenhar(g2);
		}
		if (EstadoAtual == 1 ) {
			tFase.desenhar(g2);

		}
		if (EstadoAtual == 2 ) {
			//Sair
			
		}
	}

}