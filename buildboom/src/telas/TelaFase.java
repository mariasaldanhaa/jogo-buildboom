package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controle.Jogo;

public class TelaFase extends MouseAdapter{
	Jogo jg;
	int meio,larguraComponente=200,alturaComponente=200;
	int componenteEscolhido=-1;
	public TelaFase(Jogo jg) {
		this.jg=jg;
		this.meio=jg.getAlturaTela()/2;
		
	}
	@Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        // Verifica se o jogo está na tela da Fase
        if (jg.EstadoAtual == 1) {
            boolean escolheuComponente = false;
            
            // Verifica se clicou em alguma das 3 caixas
            if (mx >= 20 && mx <= 220 && my >= 200 && my <= 400) {
                escolheuComponente = true;
            } else if (mx >= 250 && mx <= 450 && my >= 200 && my <= 400) {
                escolheuComponente = true;
            } else if (mx >= 480 && mx <= 680 && my >= 200 && my <= 400) {
                escolheuComponente = true;
            }

            // Se o jogador clicou em um componente válido, avança a fase
            if (escolheuComponente) {
                int proximaFase = jg.getFaseAtual() + 1;
                
                if (proximaFase > 5) {
                    jg.EstadoAtual = 3; // 3 será o estado da nossa TelaFinal
                } else {
                    jg.setFaseAtual(proximaFase);
                    componenteEscolhido = -1; // Reseta a seleção visual ("V") para a próxima fase
                }
            }
        }    
    }

    // Lógica de passar o mouse por cima
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (jg.EstadoAtual == 1) {
            if (mx >= 20 && mx <= 220 && my >= 200 && my <= 400) {
            	componenteEscolhido = 1;
            }
            else if (mx >= 250 && mx <= 450 && my >= 200 && my <= 400) {
            	componenteEscolhido = 2; 
            }
            else if (mx >= 480 && mx <= 680 && my >= 200 && my <= 400) {
            	componenteEscolhido = 3; 
            } 
            else {
            	componenteEscolhido= -1; 
            }
        }
    }
	public void desenhar(Graphics g2) {
		//Caixa Azul no BuilBOOM
		g2.setColor(Color.BLUE);
		g2.fillRoundRect(meio-100, 20,400, 100, 100, 100);
				
				//Build BOOM do menu
		g2.setFont(new Font("Arial", Font.BOLD, 75));
		g2.setColor(Color.YELLOW);
		g2.drawString("Fase  "+jg.getFaseAtual(), meio, 100);
				
		 g2.setFont(new Font("Arial", Font.BOLD, 40));

		//Desenhar os componentes
		g2.setColor(Color.red);
		g2.fillRoundRect(20, 200,larguraComponente, alturaComponente, 100, 100);
		
		g2.setColor(Color.yellow);
		g2.fillRoundRect(250, 200,larguraComponente, alturaComponente, 100, 100);
		
		g2.setColor(Color.green);
		g2.fillRoundRect(480, 200,larguraComponente,alturaComponente, 100, 100);
		int yTexto = 250;
		 g2.setColor(Color.white);
		        
		 g2.drawString("Comp 1", 30, yTexto);
		 if (componenteEscolhido == 1) {
			 g2.drawString("V", 70, yTexto-40); 
		     }
		        
		 g2.drawString("Comp 2", 260,  yTexto);
		     if (componenteEscolhido == 2) {
		          g2.drawString("V", 300, yTexto-40);
		     }
		        
		 g2.drawString("Comp 3", 490,  yTexto);
		     if (componenteEscolhido== 3) {
		          g2.drawString("V", 550,  yTexto-40);
		      }

	}
}
