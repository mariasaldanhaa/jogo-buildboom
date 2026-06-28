package controle;

import entidades.Cliente;
import entidades.Componente;
import entidades.TipoComponente;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorComponentes {

    private List<Componente> bancoDeDados;
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

        // PROCESSADORES
        bancoDeDados.add(new Componente(
                "Ryzen 3 4100",
                TipoComponente.PROCESSADOR,
                "AM4",
                3800,
                65));

        bancoDeDados.add(new Componente(
                "Intel i3-12100F",
                TipoComponente.PROCESSADOR,
                "LGA1700",
                4300,
                58));

        bancoDeDados.add(new Componente(
                "Athlon 3000G",
                TipoComponente.PROCESSADOR,
                "AM4",
                3500,
                35));

        // PLACAS-MÃE
        bancoDeDados.add(new Componente(
                "ASUS Prime B450M",
                TipoComponente.PLACA_MAE,
                "AM4",
                3200,
                0));

        bancoDeDados.add(new Componente(
                "Gigabyte B550M DS3H",
                TipoComponente.PLACA_MAE,
                "AM4",
                3600,
                0));

        bancoDeDados.add(new Componente(
                "ASRock H610M",
                TipoComponente.PLACA_MAE,
                "LGA1700",
                3200,
                0));

        // MEMÓRIAS RAM
        bancoDeDados.add(new Componente(
                "8GB DDR4 Kingston",
                TipoComponente.MEMORIA_RAM,
                "DDR4",
                2666,
                5));

        bancoDeDados.add(new Componente(
                "16GB DDR4 Corsair",
                TipoComponente.MEMORIA_RAM,
                "DDR4",
                3200,
                5));

        bancoDeDados.add(new Componente(
                "32GB DDR4 HyperX",
                TipoComponente.MEMORIA_RAM,
                "DDR4",
                3600,
                6));

        // FONTES
        bancoDeDados.add(new Componente(
                "Fonte 500W Vinik",
                TipoComponente.FONTE,
                "ATX",
                0,
                500));

        bancoDeDados.add(new Componente(
                "Fonte 650W Corsair",
                TipoComponente.FONTE,
                "ATX",
                0,
                650));

        bancoDeDados.add(new Componente(
                "Fonte 750W EVGA",
                TipoComponente.FONTE,
                "ATX",
                0,
                750));

        // GABINETES
        bancoDeDados.add(new Componente(
                "Gabinete Pichau HX300",
                TipoComponente.GABINETE,
                "ATX",
                0,
                0));

        bancoDeDados.add(new Componente(
                "Gabinete Gamer Mancer",
                TipoComponente.GABINETE,
                "ATX",
                0,
                0));

        bancoDeDados.add(new Componente(
                "Gabinete Office Compact",
                TipoComponente.GABINETE,
                "MicroATX",
                0,
                0));
        // ===== PROCESSADORES =====
        Componente cpu1 = new Componente("Ryzen 3 4100", TipoComponente.PROCESSADOR, "AM4", 3800,0, 65,3200);
        Componente cpu2 = new Componente("Intel i3-12100F", TipoComponente.PROCESSADOR, "LGA1700", 4300,0, 58,3200);
        Componente cpu3 = new Componente("Athlon 3000G", TipoComponente.PROCESSADOR, "AM4", 3500,0, 35,2933);
        
        bancoDeDados.add(cpu1);
        bancoDeDados.add(cpu2);
        bancoDeDados.add(cpu3);
        
        // ===== PLACA-MÃE =====
        
        Componente placa1 = new Componente("ASUS Prime B450M", TipoComponente.PLACA_MAE, "AM4", 3200, 0, 0, 0);
        Componente placa2 = new Componente("Gigabyte B550M DS3H", TipoComponente.PLACA_MAE, "AM4", 3600, 0, 0, 0);
        Componente placa3 = new Componente("ASRock H610M", TipoComponente.PLACA_MAE, "LGA1700", 3200, 0, 0, 0);

        bancoDeDados.add(placa1);
        bancoDeDados.add(placa2);
        bancoDeDados.add(placa3);
        
        // ===== MEMÓRIAS RAM =====
        Componente ram1 = new Componente("8GB DDR4 Kingston", TipoComponente.MEMORIA_RAM, "DDR4", 2666,5,0,0);
        Componente ram2 = new Componente("16GB DDR4 Corsair", TipoComponente.MEMORIA_RAM, "DDR4", 3200,5,0,0);
        Componente ram3 = new Componente("32GB DDR4 HyperX", TipoComponente.MEMORIA_RAM, "DDR4", 3600, 6,0,0);
        
        bancoDeDados.add(ram1);
        bancoDeDados.add(ram2);
        bancoDeDados.add(ram3);
            
        // ===== FONTES =====
        Componente fonte1 = new Componente("Fonte 500W Vinik", TipoComponente.FONTE, "ATX", 0, 500,0,0);
        Componente fonte2 = new Componente("Fonte 650W Corsair", TipoComponente.FONTE, "ATX", 0, 650,0,0);
        Componente fonte3 = new Componente("Fonte 750W EVGA", TipoComponente.FONTE, "ATX", 0, 750,0,0);
        
        bancoDeDados.add(fonte1);
        bancoDeDados.add(fonte2);
        bancoDeDados.add(fonte3);
        
        // ===== GABINETE =====
        Componente gabinete1 = new Componente("Gabinete Pichau HX300", TipoComponente.GABINETE, "ATX", 0, 0, 0, 0);
        Componente gabinete2 = new Componente("Gabinete Gamer Mancer", TipoComponente.GABINETE, "ATX", 0, 0, 0, 0);
        Componente gabinete3 = new Componente("Gabinete Office Compact", TipoComponente.GABINETE, "MicroATX", 0, 0, 0, 0);

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

    public int validarCompatibilidade(
            Componente cpu,
            Componente placaMae,
            Componente ram,
            Componente fonte,
            Componente gabinete) {

        if (cpu == null ||
            placaMae == null ||
            ram == null ||
            fonte == null ||
            gabinete == null) {

            return 2;
        }

        // Socket incompatível
        if (!cpu.getSocket().equals(placaMae.getSocket())) {
            return 2;
        }

        // RAM acima do suportado pela placa
        if (ram.getFrequencia() > placaMae.getFrequencia()) {
            return 2;
        }

        // Fonte insuficiente
        int consumoEstimado = cpu.getPotencia() + 150;

        if (fonte.getPotencia() < consumoEstimado) {
            return 3;
        }

        return 1;
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