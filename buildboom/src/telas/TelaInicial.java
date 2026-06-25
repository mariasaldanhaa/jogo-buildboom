package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import controle.Jogo;

public class TelaInicial extends MouseAdapter {
	Jogo jg;
	int meio;
	int larguraTela;
    int alturaTela;
    private Image imagemElipse;
	public TelaInicial(Jogo jg) {
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
	@Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (jg.EstadoAtual == 0) {
            // Clique no botão 1 (Começar)
            if (mx >= 60 && mx <= 300 && my >= 260 && my <= 310) {
            	// Sorteia o cliente da rodada
            	jg.sortearCliente();
            	
                jg.mudarParaFase(); // Chama a transição de forma segura aqui!
            }         if (mx >= 60 && mx <= 300 && my >= 260 && my <= 310) {
                jg.EstadoAtual = 1; 
                
            }
            // Clique no botão 2
            else if (mx >= 60 && mx <= 300 && my >= 360 && my <= 410) {
                jg.EstadoAtual = 2; 
            }
            // Clique no botão Sair
            else if (mx >= 60 && mx <= 300 && my >= 460 && my <= 510) {
                System.exit(0); 
            }
        }	
    }

    // Lógica de passar o mouse por cima (hover) movida para cá
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (jg.EstadoAtual == 0) {
            if (mx >= 60 && mx <= 300 && my >= 260 && my <= 310) {
                jg.opçãoSelecionada = 0;
            }
            else if (mx >= 60 && mx <= 300 && my >= 360 && my <= 410) {
                jg.opçãoSelecionada = 1; 
            }
            else if (mx >= 60 && mx <= 300 && my >= 460 && my <= 510) {
                jg.opçãoSelecionada = 2; 
            } 
            else {
                jg.opçãoSelecionada = -1; 
            }
        }
    }
	public void desenhar(Graphics g2) {
		// fundo
        g2.setColor(new Color(245, 240, 230));
        //elipses do titulo e opçoes
        g2.fillRect(0, 0, larguraTela, alturaTela);
        if (imagemElipse != null) {
            int elipseX = 400;
            int elipseY = -150;
            //titulo
            g2.drawImage(imagemElipse, elipseX, elipseY, 720, 500, null);
            //opcoes
            g2.drawImage(imagemElipse, -30, -70, 450, 850, null);
        }
      
		//Build BOOM do menu
		 g2.setFont(new Font("Arial", Font.BOLD, 75));
		 g2.setColor(Color.white);
		 g2.drawString("BuildBOOM", 550, 150);		
		 g2.setFont(new Font("Arial", Font.BOLD, 40));

		 // Opções do inicio do jogo
		 int xTexto = 100;
		 g2.setColor(Color.white);
		        
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
