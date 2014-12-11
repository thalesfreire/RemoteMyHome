package br.com.remotemyhome.model;

public class Rele {

	private Integer id;
	
	private Integer portaLogica;
	
	private String descricao;
	
	private Compartimento compartimento;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPortaLogica() {
		return portaLogica;
	}

	public void setPortaLogica(Integer portaLogica) {
		this.portaLogica = portaLogica;
	}

	public String getDescricao() {
		
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Compartimento getCompartimento() {
		return compartimento;
	}

	public void setCompartimento(Compartimento compartimento) {
		this.compartimento = compartimento;
	}

}
