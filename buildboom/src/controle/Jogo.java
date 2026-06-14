package controle;

import java.awt.Color;
import java.awt.Dimension;
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

public class Jogo extends JPanel implements Runnable {
	private int larguraTela = 1000;
	private int alturaTela = 600;
	private int FPS = 45;

	Thread gameThread;
	
	TelaInicial tInicial;
	TelaFase tFase;
    TelaFinal tFinal;
	
	public int EstadoAtual = 0; 
	public int opçãoSelecionada = -1; 
	public int faseAtual = 1;
	
	// CHAVE: Variáveis de controle para armazenar o resultado da montagem gráfica
	public int resultadoMontagemAtual = 0; // 0 = Não testado, 1 = Sucesso, 2 = Incompatível, 3 = Explodiu
	
	public Jogo() {
	        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
	        this.setBackground(Color.gray);
	        this.setDoubleBuffered(true);
	        this.setFocusable(true);
	        
	        this.tInicial = new TelaInicial(this);
	        this.tFase = new TelaFase(this);
	        this.tFinal = new TelaFinal(this);
	        
	        this.addMouseListener(tInicial);
	        this.addMouseMotionListener(tInicial);
	        
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
		// O loop roda em tempo real atualizando as mecânicas visuais aqui
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        
        // Desenhando a tela de acordo com o estado
        if (EstadoAtual == 0 ) {
            tInicial.desenhar(g2);
        } 
        else if (EstadoAtual == 1 ) {
            // ATENÇÃO: O mudarParaFase() foi removido daqui para não travar o PC!
            tFase.desenhar(g2);
        }
        else if (EstadoAtual == 3 ) {
            tFinal.desenhar(g2); // Desenha a tela final ao passar da fase 5
        }
    }

    // O método mudarParaFase() continua existindo igualzinho você fez
    public void mudarParaFase() {
        this.removeMouseListener(tInicial);
        this.removeMouseMotionListener(tInicial);
        this.EstadoAtual = 1;
        this.addMouseListener(tFase);
        this.addMouseMotionListener(tFase);
    }
}
