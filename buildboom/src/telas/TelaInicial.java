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
    private Image imagemLogo;
    private Image imagemSeta;
    private Image imagemFundo;
    private Image imagemFundoInicial;
    
	public TelaInicial(Jogo jg) {
		this.jg=jg;
		this.meio = larguraTela / 2;
		this.larguraTela = jg.getLarguraTela();
	    this.alturaTela = jg.getAlturaTela();
	    
	    try {
	        imagemFundo = new ImageIcon(
	            getClass().getResource("/assets/fundoInicial.png")
	        ).getImage();
	    } catch (Exception e) {
	        imagemFundo = null;
	    }
	    
	    try {
	    	imagemFundoInicial = new ImageIcon(
	    			getClass().getResource("/assets/comanda_inicial.png")).getImage();
	    } catch (Exception e) {
	    	imagemFundoInicial = null;
	    }
	    
	    try {
	        imagemSeta = new ImageIcon(
	            getClass().getResource("/assets/seta_opcoes.png")
	        ).getImage();
	    } catch (Exception e) {
	        imagemSeta = null;
	    }
	    
	    try {
	        imagemLogo = new ImageIcon(
	            getClass().getResource("/assets/logo_buildboom.png")
	        ).getImage();
	    } catch (Exception e) {
	        imagemLogo = null;
	        System.out.println("Imagem da logo não encontrada!");
	    }
	}
	@Override
	public void mousePressed(MouseEvent e) {

	    int mx = e.getX();
	    int my = e.getY();

	    int painelX = 170;
	    int painelLargura = 630;

	    int larguraBotao = 250;
	    int xBotao = painelX + (painelLargura - larguraBotao) / 2;

	    if (jg.EstadoAtual == 0) {
	        // Começar
	    	if (mx >= xBotao && mx <= xBotao + larguraBotao &&
	            my >= 335 && my <= 375) {

	            jg.sortearCliente();
	            jg.mudarParaFase();
	        }

	        // Opções
	        else if (mx >= xBotao && mx <= xBotao + larguraBotao &&
	                 my >= 392 && my <= 432) {

	            jg.EstadoAtual = 2;
	        }

	        // Sair
	        else if (mx >= xBotao && mx <= xBotao + larguraBotao &&
	                 my >= 450 && my <= 490) {

	            System.exit(0);
	        }
	    }
	}

    // Lógica de passar o mouse por cima (hover) movida para cá
	@Override
	public void mouseMoved(MouseEvent e) {

	    int mx = e.getX();
	    int my = e.getY();

	    int painelX = 170;
	    int painelLargura = 630;

	    int larguraBotao = 250;
	    int xBotao = painelX + (painelLargura - larguraBotao) / 2;
	    
	    if (jg.EstadoAtual == 0) {
	    	if (mx >= xBotao && mx <= xBotao + larguraBotao & my >= 335 && my <= 375) {
	    		    jg.opçãoSelecionada = 0;
	    		}
	    	
	    		else if (mx >= xBotao && mx <= xBotao + larguraBotao && my >= 392 && my <= 432) {
	    		    jg.opçãoSelecionada = 1;
	    		}

	    		else if (mx >= xBotao && mx <= xBotao + larguraBotao && my >= 450 && my <= 490) {
	    		    jg.opçãoSelecionada = 2;
	    		}

	    		else {
	    		    jg.opçãoSelecionada = -1;
	    		}
	    }
	}
	public void desenhar(Graphics g2) {
		// variáveis do painel que serão usadas para a logo e para as opções
		int painelX = 170;
		int painelY = -20;
		int painelLargura = 630;
		int painelAltura = 630;
		
		// fundo
		if (imagemFundo != null) {
		    g2.drawImage(imagemFundo, 0, 0, larguraTela, alturaTela, null);
		} else {
		    g2.setColor(new Color(245, 240, 230));
		    g2.fillRect(0, 0, larguraTela, alturaTela);
		}
		
		// retangulo branco
		if (imagemFundoInicial != null) {
			g2.drawImage(imagemFundoInicial, painelX - 6, painelY, painelLargura, painelAltura, null);
		} else {
			g2.setColor(Color.WHITE);
			g2.fillRoundRect(250, 30, 500, 540, 20, 20);
		}
		
		//Build BOOM do menu
        if (imagemLogo != null) {
            int larguraLogo = 245;
            int alturaLogo = 245;

            int xLogo = painelX + (painelLargura - larguraLogo) / 2;
            int yLogo = 95;

            g2.drawImage(imagemLogo, xLogo, yLogo, larguraLogo, alturaLogo, null);
        }

		 // Opções do inicio do jogo
        g2.setFont(new Font("Monospaced", Font.BOLD, 30));

        String[] opcoes = {
            "Começar",
            "Opções",
            "Sair"
        };

        int[] y = {
        		355,
        		412,
        		470
        };

        for (int i = 0; i < opcoes.length; i++) {

            int larguraTexto = g2.getFontMetrics().stringWidth(opcoes[i]);

            int xTexto = painelX + (painelLargura - larguraTexto) / 2;

            g2.setColor(Color.black);
            g2.drawString(opcoes[i], xTexto, y[i]);
            
            if (jg.opçãoSelecionada == i) {
                if (imagemSeta != null) {
                	int larguraSeta = 40;
                	int alturaSeta = 40;

                	g2.drawImage(imagemSeta,
                	        xTexto - larguraSeta - 15,
                	        y[i] - 30,
                	        larguraSeta,
                	        alturaSeta,
                	        null);
                } else {
                    g2.drawString(">", xTexto - 40, y[i]);
                }
            }
        }
	}
}