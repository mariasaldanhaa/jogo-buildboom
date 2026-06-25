package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controle.GerenciadorComponentes;
import controle.Jogo;

public class TelaFinal {
	Jogo jg;
	private int resultado;
	public TelaFinal(Jogo jg) {
		this.jg=jg;
	}
	public void setResultado(int resultado) {
		this.resultado=resultado;
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
	public void informarCompatibilidade(Graphics g2) {
		g2.setFont(new Font("Arial", Font.BOLD, 75));
		g2.setColor(Color.YELLOW);
		

        if(resultado==0){
        	g2.drawString("Cliente satisfeito", 550, 100);
        }
        else if(resultado==1){
        	g2.drawString("PC funciona, mas não atende ao pedido", 550, 100);    
        }
        else if(resultado==2){
        	g2.drawString("Componentes incompatíveis", 550, 100);        	
        }
        else{
        	g2.drawString("A bancada explodiu", 550, 100);         		
	}
}
}