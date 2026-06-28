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
	private Image imagemRetangulo;
	private Image imagemExplosao;
	
	public TelaFinal(Jogo jg) {
		this.jg=jg;
		this.meio = larguraTela / 2;
		this.larguraTela = jg.getLarguraTela();
	    this.alturaTela = jg.getAlturaTela();
		try {
            imagemRetangulo = new ImageIcon(
                getClass().getResource("/assets/retangulo.png")
            ).getImage();
        } catch (Exception e) {
            imagemRetangulo = null;
        }
		
		try {
		    imagemExplosao = new ImageIcon(
		        getClass().getResource("/assets/explosao.png")
		    ).getImage();
		} catch (Exception e) {
		    imagemExplosao = null;
		}
	}
	public void setResultado(int resultado) {
		this.resultado=resultado;
	}
	public void desenhar(Graphics g2) {
		int elipseX = (larguraTela - 200) / 2;  
        int elipseY = -100;     
        int elipseL= 400;
        int elipseA=400;
        int elipseM=elipseL/4;
        // fundo
        g2.setColor(new Color(245, 240, 230));
        g2.fillRect(0, 0, larguraTela, alturaTela);
        
        // retangulo do FIM
        g2.drawImage(imagemRetangulo, elipseX - meio-elipseM + 30, elipseY, elipseL, elipseA, null);
		
        //texto FIM
		g2.setFont(new Font("Arial", Font.BOLD, 75));
		g2.setColor(Color.white);
		g2.drawString("Fim", elipseX-meio+65, 130);
		informarCompatibilidade(g2);
	}
	public void informarCompatibilidade(Graphics g2) {
	    g2.setFont(new Font("Monospaced", Font.BOLD, 30));
	    g2.setColor(Color.pink);

	    if(resultado == 0){
	        g2.drawString("RESULTADO: Cliente satisfeito", 200, 350);
	    }
	    else if(resultado == 1){
	        g2.drawString("RESULTADO: PC funciona, mas não atende ao pedido", 200, 350);
	    }
	    else if(resultado == 2){
	        g2.drawString("RESULTADO: Componentes incompatíveis", 200, 350);
	    }
	    else if(resultado == 3){
	    	if(imagemExplosao != null){
	    		g2.drawImage(
	    			    imagemExplosao,
	    			    0,                 
	    			    0,                     
	    			    jg.getLarguraTela(),    
	    			    jg.getAlturaTela(),    
	    			    null
	    		);
	        }
	        g2.drawString("RESULTADO: A bancada explodiu", 20, 400);
	    }
	}
}