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

//A classe jogo é filha da classe JPanel, ou seja, herda os seus métodos e atributos.
public class Jogo extends JPanel implements Runnable {
	//Dimensões
	private int larguraTela=1000;
	private int alturaTela=600;
	private int FPS=45;
	
	
/*A classe Thread é nativa do Java, servindo para executar ações em paralelo,
como por exemplo renderizar os objetos.*/
	Thread gameThread;
	
	// Variaveis do controle da Tela
	public int EstadoAtual = 0; // 0 = Menu, 1 = Jogando, 2 = Opções
	public int opçãoSelecionada = -1; // 0 = Jogar, 1 = Opções, 2 = Sair
	
	
	public Jogo() {
		this.setPreferredSize(new Dimension(larguraTela,alturaTela));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		
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
	}

	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	
	public void Iniciar() {
		String[][] opcoes = { 
				// opções quaisquer para teste
				{
					"Ryzen 3",	
					"Intel i3",
					"Athlon"
				},
				{
					"Ryzen 5", 
					"Intel i5",
					"Xeon"
				},
				{
					"Ryzen 7",
					"Intel i7",
					"Threadripper"
				}
		};
		
		String[] escolhas = new String[3];
		Scanner sc = new Scanner(System.in);
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
			desenharMenu(g2);
		}
		if (EstadoAtual == 1 ) {
			//Jogo em sí

		}
		if (EstadoAtual == 2 ) {
			//Sair
			
		}
		
	
	}
	private void desenharMenu(Graphics2D g2) {
	
		//Caixa Azul no BuilBOOM
		g2.setColor(Color.BLUE);
		g2.fillRoundRect(530, 20, 460, 200, 100, 100);
		
		//Build BOOM do menu
		g2.setFont(new Font("Arial", Font.BOLD, 75));
        g2.setColor(Color.YELLOW);
        g2.drawString("BuildBOOM", 550, 100);
		
        g2.setFont(new Font("Arial", Font.BOLD, 40));

        //Caixa Azul das opções iniciais
		g2.setColor(Color.BLUE);
		g2.fillRoundRect(20, 200, 400, 350, 100, 100);



        // Opções do inicio do jogo
        int xTexto = 100;
        g2.setColor(Color.YELLOW);
        
        g2.drawString("Começar", xTexto, 300);
        if (opçãoSelecionada == 0) {
            g2.drawString(">", xTexto - 40, 300); 
        }
        
        g2.drawString("Opções", xTexto, 400);
        if (opçãoSelecionada == 1) {
            g2.drawString(">", xTexto - 40, 400);
        }
        
        g2.drawString("Sair", xTexto, 500);
        if (opçãoSelecionada == 2) {
            g2.drawString(">", xTexto - 40, 500);
        }
	}
}