package controle;

import entidades.Componente;
import entidades.TipoComponente;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorComponentes {
    private List<Componente> bancoDeDados;

    public GerenciadorComponentes() {
        bancoDeDados = new ArrayList<>();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        bancoDeDados.add(new Componente("Ryzen 3 4100", TipoComponente.PROCESSADOR, "AM4", 3800, 65));
        bancoDeDados.add(new Componente("Intel i3-12100F", TipoComponente.PROCESSADOR, "LGA1700", 4300, 58));
        bancoDeDados.add(new Componente("Athlon 3000G", TipoComponente.PROCESSADOR, "AM4", 3500, 35));

        bancoDeDados.add(new Componente("8GB DDR4 Kingston", TipoComponente.MEMORIA_RAM, "DDR4", 2666, 5));
        bancoDeDados.add(new Componente("16GB DDR4 Corsair", TipoComponente.MEMORIA_RAM, "DDR4", 3200, 5));
        bancoDeDados.add(new Componente("32GB DDR4 HyperX", TipoComponente.MEMORIA_RAM, "DDR4", 3600, 6));

        bancoDeDados.add(new Componente("Fonte 500W Vinik", TipoComponente.FONTE, "ATX", 0, 500));
        bancoDeDados.add(new Componente("Fonte 650W Corsair", TipoComponente.FONTE, "ATX", 0, 650));
        bancoDeDados.add(new Componente("Fonte 750W EVGA", TipoComponente.FONTE, "ATX", 0, 750));
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
