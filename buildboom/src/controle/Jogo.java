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
import telas.TelaFinal;
import telas.TelaInicial;
import entidades.*;

// A classe jogo é filha da classe JPanel, ou seja, herda os seus métodos e atributos.
public class Jogo extends JPanel implements Runnable {
	// Dimensões
	private int larguraTela = 1000;
	private int alturaTela = 600;
	private int FPS = 45;

/*A classe Thread é nativa do Java, servindo para executar ações em paralelo,
como por exemplo renderizar os objetos.*/
	Thread gameThread;
	
	TelaInicial tInicial;
	TelaFase tFase;
	TelaFinal tFinal;
// Variaveis do controle da Tela
	public int EstadoAtual = 0; // 0 = Menu, 1 = Jogando, 2 = Opções
	public int opçãoSelecionada = -1; // 0 = Jogar, 1 = Opções, 2 = Sair
	private int faseAtual=1;
	
	public Jogo() {
		this.setPreferredSize(new Dimension(larguraTela, alturaTela));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.tInicial= new TelaInicial(this);
		this.tFase= new TelaFase(this);
		this.tFinal=new TelaFinal(this);
		
		this.addMouseListener(this.tInicial);
	    this.addMouseMotionListener(this.tInicial);
	}
	
	public int getLarguraTela() {
		return larguraTela;
	}
	
	public int getAlturaTela() {
		return alturaTela;
	}
	public int getFaseAtual() {
		return faseAtual;
	}
	public void setFaseAtual(int faseAtual) {
		this.faseAtual=faseAtual;
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
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
		Graphics2D g2=(Graphics2D)g;
		//Parte do codigo que muda a tela de acordo com a opção
		
		// Parte do código que muda a tela de acordo com a opção
		if (EstadoAtual == 0 ) {
			// Menu inicial
			tInicial.desenhar(g2);
		}
		if (EstadoAtual == 1 ) {
			mudarParaFase();
			if(faseAtual<=5)
				tFase.desenhar(g2);
			else
				tFinal.desenhar(g2);
		}
		if (EstadoAtual == 2 ) {
			// Sair
		}
	}
	public void mudarParaFase() {
	    // Remove os controles da tela inicial
	    this.removeMouseListener(tInicial);
	    this.removeMouseMotionListener(tInicial);
	    
	    // Altera o estado
	    this.EstadoAtual = 1;
	    
	    // Adiciona os controles da fase
	    this.addMouseListener(tFase);
	    this.addMouseMotionListener(tFase);
	}

}
