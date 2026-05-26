package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controle.Jogo;

public class TelaInicial {
	Jogo jg;
	public TelaInicial(Jogo jg) {
		this.jg=jg;
	}
	public void desenhar(Graphics g2) {
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
		 if (jg.opçãoSelecionada == 0) {
			 g2.drawString(">", xTexto - 40, 300); 
		     }
		        
		 g2.drawString("Opções", xTexto, 400);
		     if (jg.opçãoSelecionada == 1) {
		          g2.drawString(">", xTexto - 40, 400);
		     }
		        
		 g2.drawString("Sair", xTexto, 500);
		     if (jg.opçãoSelecionada == 2) {
		          g2.drawString(">", xTexto - 40, 500);
		      }
	}
}
