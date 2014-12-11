package br.com.remotemyhome.model;

import java.util.List;

public class Compartimento {

	private Integer idCompartimento;
	
	private String nome;
	
	private Integer tipo;
	
	private List<Rele> reles;
	
	public Integer getIdCompartimento() {
		return idCompartimento;
	}
	public void setIdCompartimento(Integer idCompartimento) {
		this.idCompartimento = idCompartimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public List<Rele> getReles() {
		return reles;
	}
	public void setReles(List<Rele> reles) {
		this.reles = reles;
	}
	
}
