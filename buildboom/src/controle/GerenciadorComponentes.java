package controle;

import entidades.Componente;
import entidades.TipoComponente;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorComponentes {
    private List<Componente> bancoDeDados;
    private List<Componente> componentesCorretos; // NOVO: lista dos corretos
    public static final int SUCESSO = 1;
    public static final int INCOMPATIVEL = 2;
    public static final int EXPLODIU = 3;

    public GerenciadorComponentes() {
        bancoDeDados = new ArrayList<>();
        componentesCorretos = new ArrayList<>(); // NOVO
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // ===== PROCESSADORES =====
        Componente cpu1 = new Componente("Ryzen 3 4100", TipoComponente.PROCESSADOR, "AM4", 3800,0, 65,3200);
        Componente cpu2 = new Componente("Intel i3-12100F", TipoComponente.PROCESSADOR, "LGA1700", 4300,0, 58,3200);
        Componente cpu3 = new Componente("Athlon 3000G", TipoComponente.PROCESSADOR, "AM4", 3500,0, 35,2933);
        
        bancoDeDados.add(cpu1);
        bancoDeDados.add(cpu2);
        bancoDeDados.add(cpu3);
        
        // Define o CORRETO para a fase 0 (Processador)
        
        componentesCorretos.add(cpu2); 

        // ===== MEMÓRIAS RAM =====
        Componente ram1 = new Componente("8GB DDR4 Kingston", TipoComponente.MEMORIA_RAM, "DDR4", 2666,5,0,0);
        Componente ram2 = new Componente("16GB DDR4 Corsair", TipoComponente.MEMORIA_RAM, "DDR4", 3200,5,0,0);
        Componente ram3 = new Componente("32GB DDR4 HyperX", TipoComponente.MEMORIA_RAM, "DDR4", 3600, 6,0,0);
        
        bancoDeDados.add(ram1);
        bancoDeDados.add(ram2);
        bancoDeDados.add(ram3);
        
        // Define o CORRETO para a fase 1 (Memória RAM)
        
        componentesCorretos.add(ram2);

        // ===== FONTES =====
        Componente fonte1 = new Componente("Fonte 500W Vinik", TipoComponente.FONTE, "ATX", 0, 500,0,0);
        Componente fonte2 = new Componente("Fonte 650W Corsair", TipoComponente.FONTE, "ATX", 0, 650,0,0);
        Componente fonte3 = new Componente("Fonte 750W EVGA", TipoComponente.FONTE, "ATX", 0, 750,0,0);
        
        bancoDeDados.add(fonte1);
        bancoDeDados.add(fonte2);
        bancoDeDados.add(fonte3);
        
        // Define o CORRETO para a fase 2 (Fonte)
        componentesCorretos.add(fonte2); 
    }

    public List<Componente> obterOpcoesParaRodada(int subRodada) {
        List<Componente> opcoes = new ArrayList<>();
        TipoComponente tipoBuscado;

        if (subRodada == 0) tipoBuscado = TipoComponente.PROCESSADOR;
        else if (subRodada == 1) tipoBuscado = TipoComponente.MEMORIA_RAM;
        else tipoBuscado = TipoComponente.FONTE;

        for (Componente c : bancoDeDados) {
            if (c.getTipo() == tipoBuscado) {
                opcoes.add(c);
            }
        }
        return opcoes;
    }

    // NOVO MÉTODO: Retorna o componente correto para cada fase
    public Componente obterComponenteCorreto(int subRodada) {
        if (subRodada >= 0 && subRodada < componentesCorretos.size()) {
            return componentesCorretos.get(subRodada);
        }
        return null;
    }


 // CHAVE: Validação otimizada usando barreiras de guarda (Guard Clauses)
    public int validarCompatibilidade(Componente cpu, Componente ram, Componente fonte) {

        if (cpu == null || ram == null || fonte == null) {
            return INCOMPATIVEL;
        }

        // CPU de maior consumo
        if (fonte.getPotencia() < cpu.getConsumo() + 100) {
            return EXPLODIU;
        }

        // INCOMPATIBILIDADE DE MEMÓRIA
        if (ram.getFrequencia() > cpu.getFrequenciaMaxRam()) {
            return INCOMPATIVEL;
        }

        return SUCESSO;
    }
}