package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import controle.Jogo;

public class TelaFinal {
	Jogo jg;
	int meio;
	int larguraTela;
    int alturaTela;
	private int resultado;
	private Image imagemElipse;
	public TelaFinal(Jogo jg) {
		this.jg=jg;
		this.meio = larguraTela / 2;
		this.larguraTela = jg.getLarguraTela();
	    this.alturaTela = jg.getAlturaTela();
		try {
            imagemElipse = new ImageIcon(
                getClass().getResource("/assets/elipse.png")
            ).getImage();
        } catch (Exception e) {
            imagemElipse = null;
            System.out.println("Imagem da elipse não encontrada! Usando texto.");
        }
	}
	public void setResultado(int resultado) {
		this.resultado=resultado;
	}
	public void desenhar(Graphics g2) {
		int elipseX = (larguraTela - 200) / 2;  
        int elipseY = -100;     
        int elipseL= 500;
        int elipseA=400;
        int elipseM=elipseL/4;
        // fundo
        g2.setColor(new Color(245, 240, 230));
        g2.fillRect(0, 0, larguraTela, alturaTela);
        
        //elipse do FIM
        g2.drawImage(imagemElipse, elipseX - meio-elipseM, elipseY, elipseL, elipseA, null);
		
        //texto FIM
		g2.setFont(new Font("Arial", Font.BOLD, 75));
		g2.setColor(Color.white);
		g2.drawString("Fim", elipseX-meio+75, 135);
		informarCompatibilidade(g2);
	}
	public void informarCompatibilidade(Graphics g2) {
	    g2.setFont(new Font("Arial", Font.BOLD, 30));
	    g2.setColor(Color.pink);

	    if(resultado == 0){
	        g2.drawString("Cliente satisfeito", 200, 300);
	    }
	    else if(resultado == 1){
	        g2.drawString("PC funciona, mas não atende ao pedido", 200, 300);
	    }
	    else if(resultado == 2){
	        g2.drawString("Componentes incompatíveis", 200, 300);
	    }
	    else if(resultado == 3){
	        g2.drawString("A bancada explodiu", 200, 300);
	    }
	}
}