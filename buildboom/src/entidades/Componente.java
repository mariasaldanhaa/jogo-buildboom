package entidades;

public class Componente {
    private String nome;
    private TipoComponente tipo;
    private String socket;       // Ex: "AM4", "LGA1700"
    private int frequencia;      // Em MHz (para RAM ou CPU)
    private int potencia;	// Em Watts (para Fonte)
    private int consumo;
    private int frequenciaMaxRam;
   

    public Componente(String nome, TipoComponente tipo, String socket, 
    				  int frequencia, int potencia,int consumo,int frequenciaMaxRam) {
    	this.nome = nome;
        this.tipo = tipo;
        this.socket = socket;
        this.frequencia = frequencia;
        this.potencia = potencia;
        this.consumo=consumo;
        this.frequenciaMaxRam=frequenciaMaxRam;
    }

    // Getters
    public String getNome() { return nome; }
    public TipoComponente getTipo() { return tipo; }
    public String getSocket() { return socket; }
    public int getFrequencia() { return frequencia; }
    public int getPotencia() { return potencia; }
    public int getConsumo() {return consumo;}
    public int getFrequenciaMaxRam() {return frequenciaMaxRam;}

    @Override
    public String toString() {
        return nome;
    }
}