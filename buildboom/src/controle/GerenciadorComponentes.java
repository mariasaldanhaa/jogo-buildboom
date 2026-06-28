package controle;

import entidades.Cliente;
import entidades.Componente;
import entidades.TipoComponente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

public class GerenciadorComponentes {
	private final List<Componente> bancoDeDados;
	private Cliente clienteAtual;
	private static final int MARGEM_SEGURANCA = 100;
	public static final int SUCESSO = 1;
    public static final int INCOMPATIVEL = 2;
    public static final int EXPLODIU = 3;

    public GerenciadorComponentes() {
        bancoDeDados = new ArrayList<>();
        inicializarComponentes();
    }
    public void setClienteAtual(Cliente cliente) {
        this.clienteAtual = cliente;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }
    private void inicializarComponentes() {
        // ===== PROCESSADORES =====
    	
    	// imagens dos processadores
    	
    	// cpu1
    	Image imgRyzen = new ImageIcon(
    	        getClass().getResource("/assets/cpu_ryzen.png")
    	).getImage();
        Componente cpu1 = new Componente("Ryzen 3 4100", TipoComponente.PROCESSADOR, "AM4", 3800,0, 65,3200, imgRyzen);
        
        // cpu2
        Image imgIntel = new ImageIcon(
    	        getClass().getResource("/assets/cpu_intel.png")
    	).getImage();
        Componente cpu2 = new Componente("Intel i3-12100F", TipoComponente.PROCESSADOR, "LGA1700", 4300,0, 58,3200, imgIntel);
        
        // cpu3
        Image imgAthlon = new ImageIcon(
    	        getClass().getResource("/assets/cpu_athlon.png")
    	).getImage();
        Componente cpu3 = new Componente("Athlon 3000G", TipoComponente.PROCESSADOR, "AM4", 3500,0, 35,2933, imgAthlon);
        
        bancoDeDados.add(cpu1);
        bancoDeDados.add(cpu2);
        bancoDeDados.add(cpu3);
        
        // ===== PLACA-MÃE =====
        
        // imagens das placas
        
        // placa1
        Image imgAsus = new ImageIcon(
    	        getClass().getResource("/assets/placa_asus.png")
    	).getImage();
        Componente placa1 = new Componente("ASUS Prime B450M", TipoComponente.PLACA_MAE, "AM4", 3200, 0, 0, 0, imgAsus);
        
        // placa2
        Image imgGigabyte = new ImageIcon(
    	        getClass().getResource("/assets/placa_gigabyte.png")
    	).getImage();
        Componente placa2 = new Componente("Gigabyte B550M DS3H", TipoComponente.PLACA_MAE, "AM4", 3600, 0, 0, 0, imgGigabyte);
        
        // placa3
        Image imgAsrock = new ImageIcon(
    	        getClass().getResource("/assets/placa_asrock.png")
    	).getImage();
        Componente placa3 = new Componente("ASRock H610M", TipoComponente.PLACA_MAE, "LGA1700", 3200, 0, 0, 0, imgAsrock);

        bancoDeDados.add(placa1);
        bancoDeDados.add(placa2);
        bancoDeDados.add(placa3);
        
        // ===== MEMÓRIAS RAM =====
        
        // imagens das memórias
        
        // RAM1
        Image img8 = new ImageIcon(
    	        getClass().getResource("/assets/RAM_8.png")
    	).getImage();
        Componente ram1 = new Componente("8GB DDR4 Kingston", TipoComponente.MEMORIA_RAM, "DDR4", 2666,5,0,0, img8);
        
        // RAM2
        Image img16 = new ImageIcon(
    	        getClass().getResource("/assets/RAM_16.png")
    	).getImage();
        Componente ram2 = new Componente("16GB DDR4 Corsair", TipoComponente.MEMORIA_RAM, "DDR4", 3200,5,0,0, img16);
        
        // RAM3
        Image img32 = new ImageIcon(
    	        getClass().getResource("/assets/RAM_32.png")
    	).getImage();
        Componente ram3 = new Componente("32GB DDR4 HyperX", TipoComponente.MEMORIA_RAM, "DDR4", 3600, 6,0,0, img32);
        
        bancoDeDados.add(ram1);
        bancoDeDados.add(ram2);
        bancoDeDados.add(ram3);
            
        // ===== FONTES =====
        
        // imagens das fontes
        
        // fonte1
        Image img500 = new ImageIcon(
    	        getClass().getResource("/assets/fonte500.png")
    	).getImage();
        Componente fonte1 = new Componente("Fonte 500W Vinik", TipoComponente.FONTE, "ATX", 0, 500,0,0, img500);
        
        // fonte 2
        Image img650 = new ImageIcon(
    	        getClass().getResource("/assets/fonte650.png")
    	).getImage();
        Componente fonte2 = new Componente("Fonte 650W Corsair", TipoComponente.FONTE, "ATX", 0, 650,0,0, img650);
        
        // fonte 3
        Image img750 = new ImageIcon(
    	        getClass().getResource("/assets/fonte750.png")
    	).getImage();
        Componente fonte3 = new Componente("Fonte 750W EVGA", TipoComponente.FONTE, "ATX", 0, 750,0,0, img750);
        
        bancoDeDados.add(fonte1);
        bancoDeDados.add(fonte2);
        bancoDeDados.add(fonte3);
        
        // ===== GABINETE =====
        
        // imagens dos gabinetes
        
        // gabinete 1
        Image imgPichau = new ImageIcon(
    	        getClass().getResource("/assets/Gabinete_Pichau.png")
    	).getImage();
        Componente gabinete1 = new Componente("Gabinete Pichau HX300", TipoComponente.GABINETE, "ATX", 0, 0, 0, 0, imgPichau);
        
        // gabinete 2
        Image imgGamer = new ImageIcon(
    	        getClass().getResource("/assets/Gabinete_Gamer.png")
    	).getImage();
        Componente gabinete2 = new Componente("Gabinete Gamer Mancer", TipoComponente.GABINETE, "ATX", 0, 0, 0, 0, imgGamer);
        
        // gabinete 3
        Image imgOffice = new ImageIcon(
    	        getClass().getResource("/assets/Gabinete_Office.png")
    	).getImage();
        Componente gabinete3 = new Componente("Gabinete Office Compact", TipoComponente.GABINETE, "MicroATX", 0, 0, 0, 0, imgOffice);

        bancoDeDados.add(gabinete1);
        bancoDeDados.add(gabinete2);
       	bancoDeDados.add(gabinete3);
    }

    public List<Componente> obterOpcoesParaRodada(int subRodada) {
        List<Componente> opcoes = new ArrayList<>();
        TipoComponente tipoBuscado;

        switch (subRodada) {
	        case 0:
	            tipoBuscado = TipoComponente.PROCESSADOR;
	            break;
	        case 1:
	            tipoBuscado = TipoComponente.PLACA_MAE;
	            break;
	        case 2:
	            tipoBuscado = TipoComponente.MEMORIA_RAM;
	            break;
	        case 3:
	            tipoBuscado = TipoComponente.FONTE;
	            break;
	        case 4:
	            tipoBuscado = TipoComponente.GABINETE;
	            break;
	        default:
	            return opcoes;
    }
        for (Componente c : bancoDeDados) {
            if (c.getTipo() == tipoBuscado) {
                opcoes.add(c);
            }
        }
        return opcoes;
    }

    //Retorna o componente correto para cada fase
    public boolean atendePedido(Componente cpu, Componente placa, Componente ram,Componente fonte, Componente gabinete) {
		if (clienteAtual == null) {
			return false;
		}
		return cpu.getNome().contains(clienteAtual.getProcessadorIdeal())
				&& placa.getNome().contains(clienteAtual.getPlacaIdeal())
				&& ram.getNome().contains(clienteAtual.getRamIdeal())
				&& fonte.getNome().contains(clienteAtual.getFonteIdeal())
				&& gabinete.getNome().contains(clienteAtual.getGabineteIdeal());
    }
    // Validação otimizada usando barreiras de guarda
    public int validarCompatibilidade(Componente cpu, Componente placa, Componente ram, Componente fonte, Componente gabinete) {
    	if (cpu == null || placa == null || ram == null || fonte == null || gabinete == null) {
    		return INCOMPATIVEL;
    	}
        
        // Socket
        if (!cpu.getSocket().equals(placa.getSocket())) {
            return INCOMPATIVEL;
        }
        
        // Frequência da RAM
        if (ram.getFrequencia() > placa.getFrequencia()) {
            return INCOMPATIVEL;
        }
        
        // Fonte insuficiente para o pedido do cliente
        if (clienteAtual != null) {

            if (clienteAtual.getFonteIdeal().contains("750W") && fonte.getPotencia() < 750) {
                return EXPLODIU;
            }

            if (clienteAtual.getFonteIdeal().contains("650W") && fonte.getPotencia() < 650) {
                return EXPLODIU;
            }

            if (clienteAtual.getFonteIdeal().contains("500W") && fonte.getPotencia() < 500) {
                return EXPLODIU;
            }
        }
        
        // Fonte
        if (fonte.getPotencia() < cpu.getConsumo() + MARGEM_SEGURANCA) {
            return EXPLODIU;
        }

        // INCOMPATIBILIDADE DE MEMÓRIA
        if (ram.getFrequencia() > cpu.getFrequenciaMaxRam()) {
            return INCOMPATIVEL;
        }

        return SUCESSO;
    }
}