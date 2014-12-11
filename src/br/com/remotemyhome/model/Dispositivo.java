package br.com.remotemyhome.model;

public class Dispositivo {

	private Integer id;
	
	private Compartimento compartimento;
	
	private Modelo modelo;
	
	private Integer tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Compartimento getCompartimento() {
		return compartimento;
	}

	public void setCompartimento(Compartimento compartimento) {
		this.compartimento = compartimento;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	
}
