package entidades;

public class Componente {
    private String nome;
    private TipoComponente tipo;
    private String socket;       // Ex: "AM4", "LGA1700"
    private int frequencia;      // Em MHz (para RAM ou CPU)
    private int potencia;        // Em Watts (para Fonte)

    public Componente(String nome, TipoComponente tipo, String socket, int frequencia, int potencia) {
        this.nome = nome;
        this.tipo = tipo;
        this.socket = socket;
        this.frequencia = frequencia;
        this.potencia = potencia;
    }

    // Getters
    public String getNome() { return nome; }
    public TipoComponente getTipo() { return tipo; }
    public String getSocket() { return socket; }
    public int getFrequencia() { return frequencia; }
    public int getPotencia() { return potencia; }

    @Override
    public String toString() {
        return nome;
    }
}