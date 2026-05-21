package main;
import javax.swing.JFrame;

import controle.Jogo;

public class Main {
	public static void main(String[] args) {
		
		
		
		JFrame janela=new JFrame();
		janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);
		janela.setTitle("BuildBoom");
		janela.setResizable(false);
		Jogo jogo = new Jogo();
		janela.add(jogo);
		janela.pack();
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		jogo.Iniciar();
		jogo.startGameThread();
	}
}
