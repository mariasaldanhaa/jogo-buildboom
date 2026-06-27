package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controle.Jogo;

public class Main {
	public static void main(String[] args) {

        JFrame janela = new JFrame();

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setTitle("BuildBoom");
        
        // icone do jogo
        ImageIcon icone = new ImageIcon(
            Main.class.getResource("/assets/logo_icone.png")
        );

        janela.setIconImage(icone.getImage());

        janela.setResizable(false);

        Jogo jogo = new Jogo();

        janela.add(jogo);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        jogo.startGameThread();
    }
}
