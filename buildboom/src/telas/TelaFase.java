package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controle.Jogo;

public class TelaFase {
	Jogo jg;
	int meio,larguraComponente=200,alturaComponente=200;
	int faseAtual;
	public TelaFase(Jogo jg) {
		this.jg=jg;
		this.meio=jg.getAlturaTela()/2;
		faseAtual=jg.faseAtual;
	}
	public void desenhar(Graphics g2) {
		//Caixa Azul no BuilBOOM
		g2.setColor(Color.BLUE);
		g2.fillRoundRect(meio-100, 20,400, 100, 100, 100);
				
				//Build BOOM do menu
		g2.setFont(new Font("Arial", Font.BOLD, 75));
		g2.setColor(Color.YELLOW);
		g2.drawString("Fase  "+faseAtual, meio, 100);
				
		 g2.setFont(new Font("Arial", Font.BOLD, 40));

		//Desenhar os componentes
		g2.setColor(Color.red);
		g2.fillRoundRect(20, 200,larguraComponente, alturaComponente, 100, 100);
		
		g2.setColor(Color.yellow);
		g2.fillRoundRect(250, 200,larguraComponente, alturaComponente, 100, 100);
		
		g2.setColor(Color.green);
		g2.fillRoundRect(480, 200,larguraComponente,alturaComponente, 100, 100);

	}
	public void atualizarFase() {
		faseAtual+=1;
	}
}
