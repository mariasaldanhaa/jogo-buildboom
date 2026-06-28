package controle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

import javax.swing.JPanel;

import telas.TelaFase;
import telas.TelaFinal;
import telas.TelaInicial;
import entidades.*;

public class Jogo extends JPanel implements Runnable {
	private int larguraTela = 1000;
	private int alturaTela = 600;
	private int FPS = 45;

	Thread gameThread;
	
	TelaInicial tInicial;
	TelaFase tFase;
    public TelaFinal tFinal;
    public GerenciadorComponentes gerenciador;
	
	public int EstadoAtual = 0; 
	public int opçãoSelecionada = -1; 
	public int faseAtual = 1;
	public static final int TOTAL_RODADAS = 5;
	public int rodadaAtual = 0;
	
	// Cliente que está sendo atendido atualmente
	public Cliente clienteAtual;
	
	// Lista de todos os clientes do jogo
	public ArrayList<Cliente> clientes = new ArrayList<>();

	public int resultadoMontagemAtual = 0; // 0 = Não testado, 1 = Sucesso, 2 = Incompatível, 3 = Explodiu

	public Componente componenteEscolhidoCPU = null;
	public Componente componenteEscolhiPlaca = null;
	public Componente componenteEscolhidoRAM = null;
	public Componente componenteEscolhidoFonte = null;
	public Componente componenteEscolhidoGabinete = null;
	
	public Jogo() {
	        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
	        this.setBackground(Color.gray);
	        this.setDoubleBuffered(true);
	        this.setFocusable(true);
	        
	        this.tInicial = new TelaInicial(this);
	        this.tFase = new TelaFase(this);
	        this.tFinal = new TelaFinal(this);
	        
	        // armazena todos os componentes do jogo e fornece as opções para cada fase
	        gerenciador = new GerenciadorComponentes();
	        
	        this.addMouseListener(tInicial);
	        this.addMouseMotionListener(tInicial);
		
	        // Cadastro dos clientes do jogo
	        clientes.add(new Cliente(
	        		"Matheus",
	        		"Streamer Gamer",
	        		"ㅤJogos\r\n"
	        				+ "ㅤStreaming\r\n"
	        				+ "ㅤAlto desempenho",
	        		"Ryzen 3",
	        		"Gigabyte B550M",
	        		"32GB",
	     			"750W",
	     			"Gabinete Gamer"
	        ));

	        clientes.add(new Cliente(
	        		"Dona Maria",
	        		"Aposentada",
	        		"ㅤInternet\r\n"
	        				+ "ㅤReceitas\r\n"
	        				+ "ㅤVídeos",
	        		"Athlon",
	        		"ASUS Prime B450M",
	        		"8GB",
	        		"500W",
	        		"Gabinete Office"
	        ));

	        clientes.add(new Cliente(
	        		"Carlos",
	        		"Dono de Mercado",
	        		"ㅤEstoque\r\n"
						+ "ㅤVendas\r\n"
						+ "ㅤSistema da loja",
					"Intel i3",
					"ASRock H610M",
					"16GB",
					"650W",
					"Gabinete Pichau"
		));

		clientes.add(new Cliente(
				"Gabriel",
				"Estudante Universitário",
				"ㅤEstudos\r\n"
						+ "ㅤProgramação\r\n"
						+ "ㅤTrabalhos",
				"Ryzen 3",
				"ASUS Prime B450M",
				"16GB",
				"500W",
				"Gabinete Pichau"
		));
		
		// sorteia um cliente ao iniciar
		sortearCliente();
	}
	
	// Sorteia um cliente aleatório
	public void sortearCliente() {
	    Random random = new Random();

	    int indice = random.nextInt(clientes.size());

	    clienteAtual = clientes.get(indice);
	    gerenciador.setClienteAtual(clienteAtual);

	    System.out.println("====================");
	    System.out.println("NOVO CLIENTE");
	    System.out.println("====================");

	    System.out.println("Nome: " + clienteAtual.getNome());
	    System.out.println("Profissão: " + clienteAtual.getProfissao());
	    System.out.println("Objetivo: " + clienteAtual.getObjetivo());

	    System.out.println("====================\n");
	}
	
	public int getLarguraTela() {
		return larguraTela;
	}
	
	public int getAlturaTela() {
		return alturaTela;
	}
	public int getFaseAtual() {
		return faseAtual;
	}
	public void setFaseAtual(int faseAtual) {
		this.faseAtual=faseAtual;
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double IntervaloDeDesenho = 1000000000 / FPS;
		double quandoDesenharOProximoobjeto = System.nanoTime() + IntervaloDeDesenho;
		while(gameThread != null) {
			atualizar();
			repaint();
			try {
				double tempoRestante = quandoDesenharOProximoobjeto - System.nanoTime();
				tempoRestante = tempoRestante / 1000000;
				if(tempoRestante < 0) {
					tempoRestante = 0;
				}
				Thread.sleep((long)tempoRestante);
				quandoDesenharOProximoobjeto += IntervaloDeDesenho;
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizar() {
		// O loop roda em tempo real atualizando as mecânicas visuais aqui
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        
        // Desenhando a tela de acordo com o estado
        if (EstadoAtual == 0 ) {
            tInicial.desenhar(g2);
        } 
        else if (EstadoAtual == 1 ) {
            tFase.desenhar(g2);
        }
        else if (EstadoAtual == 3 ) {
            tFinal.desenhar(g2);
        }
    }

    public void mudarParaFase() {
        this.removeMouseListener(tInicial);
        this.removeMouseMotionListener(tInicial);
        this.EstadoAtual = 1;
        this.addMouseListener(tFase);
        this.addMouseMotionListener(tFase);
        rodadaAtual = 0;

        componenteEscolhidoCPU = null;
        componenteEscolhiPlaca = null;
        componenteEscolhidoRAM = null;
        componenteEscolhidoFonte = null;
        componenteEscolhidoGabinete = null;

        resultadoMontagemAtual = 0;
        sortearCliente();
    }
    
    public void proximaRodada() {
        rodadaAtual++;

        if (rodadaAtual >= TOTAL_RODADAS) {
            resultadoMontagemAtual = gerenciador.validarCompatibilidade(
                    componenteEscolhidoCPU,
                    componenteEscolhiPlaca,
                    componenteEscolhidoRAM,
                    componenteEscolhidoFonte,
                    componenteEscolhidoGabinete);

            tFinal.setResultado(resultadoMontagemAtual);

            EstadoAtual = 3;
        }
    }
}