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

    // Cadastra o catálogo do jogo
    private void inicializarComponentes() {
        // Processadores (Nome, Tipo, Socket, Frequência, Potência requerida)
        bancoDeDados.add(new Componente("Ryzen 3 4100", TipoComponente.PROCESSADOR, "AM4", 3800, 65));
        bancoDeDados.add(new Componente("Intel i3-12100F", TipoComponente.PROCESSADOR, "LGA1700", 4300, 58));
        bancoDeDados.add(new Componente("Athlon 3000G", TipoComponente.PROCESSADOR, "AM4", 3500, 35));

        // Memórias RAM (Nome, Tipo, Socket/Padrão, Frequência, Potência)
        bancoDeDados.add(new Componente("8GB DDR4 Kingston", TipoComponente.MEMORIA_RAM, "DDR4", 2666, 5));
        bancoDeDados.add(new Componente("16GB DDR4 Corsair", TipoComponente.MEMORIA_RAM, "DDR4", 3200, 5));
        bancoDeDados.add(new Componente("32GB DDR4 HyperX", TipoComponente.MEMORIA_RAM, "DDR4", 3600, 6));

        // Fontes (Nome, Tipo, Socket, Frequência, Potência Total)
        bancoDeDados.add(new Componente("Fonte 500W Vinik", TipoComponente.FONTE, "ATX", 0, 500));
        bancoDeDados.add(new Componente("Fonte 650W Corsair", TipoComponente.FONTE, "ATX", 0, 650));
        bancoDeDados.add(new Componente("Fonte 750W EVGA", TipoComponente.FONTE, "ATX", 0, 750));
    }

    // Retorna as 3 opções específicas baseadas no tipo da sub-rodada
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

    // Validação Técnica de Compatibilidade (Regra de Negócio)
    public boolean validarCompatibilidade(Componente cpu, Componente ram, Componente fonte) {
        // Exemplo de validação: Se a fonte suporta o consumo do setup (Simulação)
        if (fonte.getPotencia() < 500 && cpu.getNome().contains("Ryzen 3")) {
            return false; 
        }
        return true;
    }
}
