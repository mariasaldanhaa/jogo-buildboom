package entidades;

public class Cliente {
	private String nome;
	private String profissao;
	private String objetivo;
	private String processadorIdeal;
	private String ramIdeal;
	private String fonteIdeal;
	
	public Cliente(String nome, 
				   String profissao,
				   String objetivo,
				   String processadorIdeal,
				   String ramIdeal,
				   String fonteIdeal) {
		this.nome = nome;
		this.profissao = profissao;
		this.objetivo = objetivo;
		this.processadorIdeal = processadorIdeal;
		this.ramIdeal = ramIdeal;
		this.fonteIdeal = fonteIdeal;
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

	public String getRamIdeal() {
		return ramIdeal;
	}

	public String getFonteIdeal() {
		return fonteIdeal;
	}
}