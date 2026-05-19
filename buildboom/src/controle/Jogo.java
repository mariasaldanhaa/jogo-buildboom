package controle;
import java.util.*;

public class Jogo {
	public void Iniciar() {
		String[][] opcoes = {
				// opções quaisquer para teste
				{
					"Ryzen 3",	
					"Intel i3",
					"Athlon"
				},
				{
					"Ryzen 5", 
					"Intel i5",
					"Xeon"
				},
				{
					"Ryzen 7",
					"Intel i7",
					"Threadripper"
				}
		};
		
		String[] escolhas = new String[3];
		Scanner sc = new Scanner(System.in);
		System.out.println("BuildBOOM iniciado!");
		
		for(int i = 0; i < 3; i++) {
			System.out.printf("Rodada %d\n", i + 1);
			
			for(int j = 0; j < 3; j++) {
				System.out.printf("%d - %s\n", j + 1, opcoes[i][j]);
			}
			System.out.printf("\nDigite sua escolha da rodada %d: ", i + 1);
			int escolha = sc.nextInt();
			
			while(escolha < 1 || escolha > 3) {
				System.out.printf("\nDigite sua escolha novamente da rodada %d: ", i + 1);
				escolha = sc.nextInt();
			}
			
			escolhas[i] = opcoes[i][escolha - 1];
		}
		
		System.out.println("\nEscolhas do jogador");
		
		for(int i = 0; i < 3; i++) {
			System.out.printf("Rodada %d: opcao %s\n", i + 1, escolhas[i]);
		}
		
		System.out.println("Fim de jogo!");
		sc.close();
	}
}