package entidades;

public class Cliente {
	private String nome;
	private String profissao;
	private String objetivo;
	private String processadorIdeal;
	private String placaIdeal;
	private String ramIdeal;
	private String fonteIdeal;
	private String gabineteIdeal;
	
	public Cliente(String nome, 
				   String profissao,
				   String objetivo,
				   String processadorIdeal,
				   String placaIdeal,
				   String ramIdeal,
				   String fonteIdeal,
				   String gabineteIdeal) {
		
		this.nome = nome;
		this.profissao = profissao;
		this.objetivo = objetivo;
		this.processadorIdeal = processadorIdeal;
		this.placaIdeal = placaIdeal;
		this.ramIdeal = ramIdeal;
		this.fonteIdeal = fonteIdeal;
		this.gabineteIdeal = gabineteIdeal;
	}

	public String getNome() {
		return nome;
	}

	public String getObjetivo() {
		return objetivo;
	}
	
	public String getProfissao() {
		return profissao;
	}

	public String getProcessadorIdeal() {
		return processadorIdeal;
	}
	
	public String getPlacaIdeal() {
		return placaIdeal;
	}

	public String getRamIdeal() {
		return ramIdeal;
	}

	public String getFonteIdeal() {
		return fonteIdeal;
	}
	
	public String getGabineteIdeal() {
		return gabineteIdeal;
	}
}