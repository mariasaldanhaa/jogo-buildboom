package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import controle.Jogo;
import entidades.Componente;
import entidades.TipoComponente;
import java.util.List;

public class TelaFase extends MouseAdapter {

    Jogo jg;
    int meio;
    int larguraTela;
    int alturaTela;
    
    int larguraComponente = 160;
    int alturaComponente = 180;
    int componenteEscolhido = -1;
    
    // Armazena os componentes escolhidos
    private Componente componenteCPU = null;
    private Componente componenteRAM = null;
    private Componente componenteFonte = null;
    
    // imagens
    private Image imagemComanda;
    private Image imagemSeta;
    private Image imagemElipse;
    
    private int[] posX;
    private int[] posY;
    private Rectangle[] rectsComponentes;
    
    public TelaFase(Jogo jg) {
        this.jg = jg;
        this.larguraTela = jg.getLarguraTela();
        this.alturaTela = jg.getAlturaTela();
        this.meio = larguraTela / 2;
        
        try {
            imagemComanda = new ImageIcon(
                getClass().getResource("/assets/comanda.png")
            ).getImage();
        } catch (Exception e) {
            imagemComanda = null;
        }
        
        try {
            imagemSeta = new ImageIcon(
                getClass().getResource("/assets/seta.png")
            ).getImage();
        } catch (Exception e) {
            imagemSeta = null;
            System.out.println("Imagem da seta não encontrada! Usando texto.");
        }
        
        try {
            imagemElipse = new ImageIcon(
                getClass().getResource("/assets/elipse.png")
            ).getImage();
        } catch (Exception e) {
            imagemElipse = null;
            System.out.println("Imagem da elipse não encontrada! Usando texto.");
        }
        
        // Posições dos componentes
        int inicioX = 380;
        int espacamento = 20;
        int totalLargura = 3 * larguraComponente + 2 * espacamento;
        int areaDireita = larguraTela - inicioX;
        int inicioComponentes = inicioX + (areaDireita - totalLargura) / 2;
        
        posX = new int[3];
        posY = new int[3];
        rectsComponentes = new Rectangle[3];
        
        for (int i = 0; i < 3; i++) {
            posX[i] = inicioComponentes + i * (larguraComponente + espacamento);
            posY[i] = 150;
            rectsComponentes[i] = new Rectangle(
                posX[i], 
                posY[i], 
                larguraComponente, 
                alturaComponente
            );
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (jg.EstadoAtual == 1) {
            for (int i = 0; i < rectsComponentes.length; i++) {
                if (rectsComponentes[i].contains(mx, my)) {
                    selecionarComponente(i);
                    break;
                }
            }
        }
    }
    
    private void selecionarComponente(int index) {
        List<Componente> opcoes = jg.gerenciador.obterOpcoesParaRodada(
            jg.getFaseAtual() - 1
        );
        
        if (index < opcoes.size()) {
            Componente selecionado = opcoes.get(index);
            int faseAtual = jg.getFaseAtual();
            
            // Armazena o componente escolhido conforme a fase
            if (faseAtual == 1) {
                componenteCPU = selecionado;
                System.out.println("CPU escolhida: " + selecionado.getNome());
                avancarFase();
            } else if (faseAtual == 2) {
                componenteRAM = selecionado;
                System.out.println("RAM escolhida: " + selecionado.getNome());
                avancarFase();
            } else if (faseAtual == 3) {
                componenteFonte = selecionado;
                System.out.println("Fonte escolhida: " + selecionado.getNome());
                verificarMontagemFinal();
            }
        }
    }
    
    private void avancarFase() {
        int proximaFase = jg.getFaseAtual() + 1;
        jg.setFaseAtual(proximaFase);
        componenteEscolhido = -1;
    }
    
    private void verificarMontagemFinal() {
        // Valida a compatibilidade dos 3 componentes
        int resultado = jg.gerenciador.validarCompatibilidade(
            componenteCPU,
            componenteRAM,
            componenteFonte
        );
        
        // Armazena o resultado no Jogo
        jg.resultadoMontagemAtual = resultado;
        
        // Armazena os componentes escolhidos no Jogo
        jg.componenteEscolhidoCPU = componenteCPU;
        jg.componenteEscolhidoRAM = componenteRAM;
        jg.componenteEscolhidoFonte = componenteFonte;
        
        // Vai para a tela de resultado
        jg.EstadoAtual = 3;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        componenteEscolhido = -1;
        if (jg.EstadoAtual == 1) {
            for (int i = 0; i < rectsComponentes.length; i++) {
                if (rectsComponentes[i].contains(mx, my)) {
                    componenteEscolhido = i;
                    break;
                }
            }
        }
    }
    
    public void desenhar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        // fundo
        g2.setColor(new Color(245, 240, 230));
        g2.fillRect(0, 0, larguraTela, alturaTela);
        
        // título
        if (imagemElipse != null) {
            int elipseX = (larguraTela - 200) / 2;  
            int elipseY = -67;     
            g2.drawImage(imagemElipse, elipseX - 325, elipseY, 250, 200, null);
            
            g2.setFont(new Font("Monospaced", Font.BOLD, 38));
            g2.setColor(Color.WHITE);
            String titulo = "Fase " + jg.getFaseAtual();
            int largTitulo = g2.getFontMetrics().stringWidth(titulo);
            g2.drawString(titulo, (meio - largTitulo/2) - 300, 53);
        } else {
            g2.setColor(new Color(172, 50, 50));
            g2.fillRoundRect(110, 15, 180, 58, 50, 50);
            
            g2.setFont(new Font("Monospaced", Font.BOLD, 32));
            g2.setColor(Color.WHITE);
            String titulo = "Fase " + jg.getFaseAtual();
            int largTitulo = g2.getFontMetrics().stringWidth(titulo);
            g2.drawString(titulo, (meio - largTitulo/2) - 300, 55);
        }
        
        // comanda
        int comandaX = 25;
        int comandaY = 80;
        int comandaLargura = 350;
        int comandaAltura = 470;
        
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(comandaX + 5, comandaY + 5, comandaLargura, comandaAltura, 10, 10);
        
        if (imagemComanda != null) {
            g2.drawImage(imagemComanda, comandaX - 210, comandaY - 88, 780, 700, null);
        }
        
        // ===== TEXTO DA COMANDA =====
        if (jg.clienteAtual != null) {
            int tx = comandaX + 25;
            int ty = comandaY + 80;
            
            // Nome
            g2.setColor(Color.pink);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Nome:", tx - 5, ty + 45);
            
            // Informação do Nome
            g2.setColor(Color.white);
            g2.setFont(new Font("Monospaced", Font.BOLD, 25));
            g2.drawString(jg.clienteAtual.getNome(), tx - 3, ty + 80);
            ty += 28;
            
            // Profissão
            g2.setColor(Color.pink);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Profissão:", tx - 5, ty + 90);
            
            // Informação da Profissão
            g2.setColor(Color.white);
            g2.setFont(new Font("Monospaced", Font.BOLD, 20));
            g2.drawString(jg.clienteAtual.getProfissao(), tx - 3, ty + 128);
            ty += 30;
            
            // Objetivo
            g2.setColor(Color.pink);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Objetivo:", tx - 5, ty + 140);
            ty += 22;
            
            g2.setColor(Color.white);
            g2.setFont(new Font("Monospaced", Font.BOLD, 18));
            String[] linhas = jg.clienteAtual.getObjetivo().split("\n");
            for (String linha : linhas) {
                if (!linha.trim().isEmpty()) {
                    g2.drawString(linha.trim(), tx, ty + 147);
                    ty += 22;
                }
            }
            
            ty += 10;
            
            // Dica do que escolher
            g2.setFont(new Font("Monospaced", Font.BOLD, 35));
            g2.setColor(new Color(0, 0, 0));
            
            int fase = jg.getFaseAtual();
            String tipoComponente = "";
            if (fase == 1) {
                tipoComponente = "o Processador";
            } else if (fase == 2) {
                tipoComponente = "a Memória RAM";
            } else if (fase == 3) {
                tipoComponente = "a Fonte";
            }
            g2.drawString("Escolha " + tipoComponente, 470, ty - 235);
            ty += 28;
        }
        
        // ===== COMPONENTES =====
        List<Componente> opcoes = jg.gerenciador.obterOpcoesParaRodada(
            jg.getFaseAtual() - 1
        );
        
        for (int i = 0; i < opcoes.size() && i < 3; i++) {
            Componente comp = opcoes.get(i);
            int x = posX[i];
            int y = posY[i];
            boolean hover = (componenteEscolhido == i);
            
            g2.setColor(new Color(0, 0, 0, 25));
            g2.fillRoundRect(x + 4, y + 4, larguraComponente, alturaComponente, 12, 12);
            
            Color corFundo = hover ? new Color(255, 220, 100) : Color.WHITE;
            g2.setColor(corFundo);
            g2.fillRoundRect(x, y, larguraComponente, alturaComponente, 12, 12);
            
            g2.setColor(hover ? new Color(200, 160, 0) : new Color(190, 185, 180));
            g2.setStroke(new java.awt.BasicStroke(hover ? 3 : 1));
            g2.drawRoundRect(x, y, larguraComponente, alturaComponente, 12, 12);
            
            // letra do componente
            Color corCirculo;
            if (jg.getFaseAtual() == 1) corCirculo = new Color(220, 80, 80);
            else if (jg.getFaseAtual() == 2) corCirculo = new Color(80, 180, 80);
            else corCirculo = new Color(80, 130, 220);
            
            g2.setColor(corCirculo);
            g2.fillOval(x + 45, y + 20, 70, 70);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            
            String letra;
            if (i == 0) letra = "R";
            else if (i == 1) letra = "I";
            else letra = "A";
            int largLetra = g2.getFontMetrics().stringWidth(letra);
            g2.drawString(letra, x + 80 - largLetra/2, y + 72);
            
            // nome do componente
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 13));
            String nome = comp.getNome();
            int largNome = g2.getFontMetrics().stringWidth(nome);
            if (largNome > larguraComponente - 20) {
                String[] partes = nome.split(" ", 2);
                if (partes.length == 2) {
                    g2.drawString(partes[0], x + (larguraComponente - g2.getFontMetrics().stringWidth(partes[0]))/2, y + 125);
                    g2.drawString(partes[1], x + (larguraComponente - g2.getFontMetrics().stringWidth(partes[1]))/2, y + 143);
                } else {
                    g2.drawString(nome, x + (larguraComponente - largNome)/2, y + 135);
                }
            } else {
                g2.drawString(nome, x + (larguraComponente - largNome)/2, y + 135);
            }
            
            // especificações
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.setColor(new Color(100, 100, 100));
            String specs = "";
            if (comp.getTipo() == TipoComponente.PROCESSADOR) {
                specs = comp.getFrequencia() + " MHz";
            } else if (comp.getTipo() == TipoComponente.MEMORIA_RAM) {
                specs = comp.getFrequencia() + " MHz";
            } else if (comp.getTipo() == TipoComponente.FONTE) {
                specs = comp.getPotencia() + "W";
            }
            int largSpecs = g2.getFontMetrics().stringWidth(specs);
            g2.drawString(specs, x + (larguraComponente - largSpecs)/2, y + 168);
            
            // hover (setinha)
            if (hover) {
                g2.setColor(new Color(255, 215, 0, 60));
                g2.fillRoundRect(x, y, larguraComponente, alturaComponente, 12, 12);
                
                if (imagemSeta != null) {
                    int setaX = x + (larguraComponente - 40) / 2;
                    int setaY = y - 45;
                    g2.drawImage(imagemSeta, setaX, setaY, 40, 40, null);
                } else {
                    g2.setColor(new Color(200, 160, 0));
                    g2.setFont(new Font("Arial", Font.BOLD, 28));
                    g2.drawString("▲", x + larguraComponente/2 - 8, y - 10);
                }
            }
            
            g2.setStroke(new java.awt.BasicStroke(1));
        }
        
        // rodapé
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.setColor(new Color(120, 110, 100));
        String msg = "Clique no componente correto para o pedido!";
        int largMsg = g2.getFontMetrics().stringWidth(msg);
        g2.drawString(msg, meio - largMsg/2, alturaTela - 20);
    }
}