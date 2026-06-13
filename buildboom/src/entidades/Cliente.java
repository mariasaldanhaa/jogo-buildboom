package entidades;

public class Cliente {
	private String nome;
	private String pedido;
	
	private String processadorIdeal;
	private String ramIdeal;
	private String fonteIdeal;
	
	public Cliente(String nome, String pedido,
				   String processadorIdeal,
				   String ramIdeal,
				   String fonteIdeal) {
		this.nome = nome;
		this.pedido = pedido;
		this.processadorIdeal = processadorIdeal;
		this.ramIdeal = ramIdeal;
		this.fonteIdeal = fonteIdeal;
	}

	public String getNome() {
		return nome;
	}

	public String getPedido() {
		return pedido;
	}

	public String getProcessadorIdeal() {
		return processadorIdeal;
	}

	public String getRamIdeal() {
		return ramIdeal;
	}

	public String getFonteIdeal() {
		return fonteIdeal;
	}
}