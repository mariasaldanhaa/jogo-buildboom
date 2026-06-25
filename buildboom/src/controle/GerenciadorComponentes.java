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

            default:
                tipoBuscado = TipoComponente.GABINETE;
                break;
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
    }
}