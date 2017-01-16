package it.spring.mvc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Persona {
	
	@NotNull
	@Size(min=1)
	private String nome;
	@NotNull
	@Size(min=3,message = "Password must more than 3 characters")
	private String cognome;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	

}
