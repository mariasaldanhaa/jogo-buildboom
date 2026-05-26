package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controle.Jogo;

public class TelaFinal {
	Jogo jg;
	public TelaFinal(Jogo jg) {
		this.jg=jg;
	}
	public void desenhar(Graphics g2) {
		//Caixa Azul no BuilBOOM
		g2.setColor(Color.BLUE);
		g2.fillRoundRect(530, 20, 460, 200, 100, 100);
				
				//Build BOOM do menu
		g2.setFont(new Font("Arial", Font.BOLD, 75));
		g2.setColor(Color.YELLOW);
		g2.drawString("Fim", 550, 100);
	}
}
