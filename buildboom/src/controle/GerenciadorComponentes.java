package controle;

import entidades.Componente;
import entidades.TipoComponente;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorComponentes {
    private List<Componente> bancoDeDados;
    private List<Componente> componentesCorretos; // NOVO: lista dos corretos

    public GerenciadorComponentes() {
        bancoDeDados = new ArrayList<>();
        componentesCorretos = new ArrayList<>(); // NOVO
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // ===== PROCESSADORES =====
        Componente cpu1 = new Componente("Ryzen 3 4100", TipoComponente.PROCESSADOR, "AM4", 3800, 65);
        Componente cpu2 = new Componente("Intel i3-12100F", TipoComponente.PROCESSADOR, "LGA1700", 4300, 58);
        Componente cpu3 = new Componente("Athlon 3000G", TipoComponente.PROCESSADOR, "AM4", 3500, 35);
        
        bancoDeDados.add(cpu1);
        bancoDeDados.add(cpu2);
        bancoDeDados.add(cpu3);
        
        // Define o CORRETO para a fase 0 (Processador)
        // Vamos usar o Intel i3-12100F como correto para o Carlos (Dono de Mercado)
        componentesCorretos.add(cpu2); // Índice 0 = Processador correto

        // ===== MEMÓRIAS RAM =====
        Componente ram1 = new Componente("8GB DDR4 Kingston", TipoComponente.MEMORIA_RAM, "DDR4", 2666, 5);
        Componente ram2 = new Componente("16GB DDR4 Corsair", TipoComponente.MEMORIA_RAM, "DDR4", 3200, 5);
        Componente ram3 = new Componente("32GB DDR4 HyperX", TipoComponente.MEMORIA_RAM, "DDR4", 3600, 6);
        
        bancoDeDados.add(ram1);
        bancoDeDados.add(ram2);
        bancoDeDados.add(ram3);
        
        // Define o CORRETO para a fase 1 (Memória RAM)
        // Vamos usar 16GB DDR4 Corsair como correto para o Carlos
        componentesCorretos.add(ram2); // Índice 1 = RAM correta

        // ===== FONTES =====
        Componente fonte1 = new Componente("Fonte 500W Vinik", TipoComponente.FONTE, "ATX", 0, 500);
        Componente fonte2 = new Componente("Fonte 650W Corsair", TipoComponente.FONTE, "ATX", 0, 650);
        Componente fonte3 = new Componente("Fonte 750W EVGA", TipoComponente.FONTE, "ATX", 0, 750);
        
        bancoDeDados.add(fonte1);
        bancoDeDados.add(fonte2);
        bancoDeDados.add(fonte3);
        
        // Define o CORRETO para a fase 2 (Fonte)
        // Vamos usar Fonte 650W Corsair como correto para o Carlos
        componentesCorretos.add(fonte2); // Índice 2 = Fonte correta
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

    // CHAVE: Validação otimizada usando barreiras de guarda (Guard Clauses) para legibilidade e performance
    public int validarCompatibilidade(Componente cpu, Componente ram, Componente fonte) {
        // Proteção contra falhas estruturais caso algum componente venha nulo da interface gráfica
        if (cpu == null || ram == null || fonte == null) {
            return 2; 
        }

        // OTIMIZAÇÃO 1: Isolamento de variáveis para evitar chamadas de métodos repetidas
        String socketCpu = cpu.getSocket();
        int potenciaFonte = fonte.getPotencia();
        int frequenciaRam = ram.getFrequencia();

        // OTIMIZAÇÃO 2: Barreira de Explosão (Falha Crítica de Energia)
        // Centraliza as condições que fazem a bancada explodir
        boolean cpuExigente = cpu.getNome().toUpperCase().contains("INTEL");
        if (potenciaFonte <= 500 && cpuExigente) {
            return 3; // 3 = EXPLODIU 💥
        }

        // OTIMIZAÇÃO 3: Barreira de Compatibilidade Técnica (Incompatibilidade de Barramento)
        // Centraliza as travas físicas e lógicas de comunicação do hardware
        boolean barramentoInvalido = socketCpu.equals("AM4") && frequenciaRam > 3200;
        if (barramentoInvalido) {
            return 2; // 2 = NÃO FUNCIONA ❌
        }

        // Se não caiu em nenhuma barreira de erro, o setup está validado
        return 1; // 1 = SUCESSO ✔️
    }
}