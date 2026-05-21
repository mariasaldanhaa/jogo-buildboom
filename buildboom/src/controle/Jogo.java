package controle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

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
	
	public Jogo() {
		this.setPreferredSize(new Dimension(larguraTela,alturaTela));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
	
	}
}