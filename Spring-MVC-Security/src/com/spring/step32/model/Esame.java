package com.spring.step32.model;

import java.util.Date;

import javax.validation.constraints.Size;


public class Esame {
	
	private int id;
	@Size(min=6, message="Enter atleast 6 characters")
	private String desc;
	private Date data;
	private boolean superato;
	private String studente;
	
	public Esame() {
		super();
	}
	
	public Esame(int id, String desc, Date data, boolean superato, String studente) {
		super();
        this.id = id;
        this.desc = desc;
        this.data = data;
        this.superato = superato;
        this.studente = studente;
    }
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public boolean isSuperato() {
		return superato;
	}
	
	public void setSuperato(boolean superato) {
		this.superato = superato;
	}
	
	public String getStudente() {
		return studente;
	}

	public void setStudente(String studente) {
		this.studente = studente;
	}
	
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esame other = (Esame) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Esame [id=%s, desc=%s, data=%s, superato=%s, studente=%s]",
				id, desc, data, superato, studente);
	}

	

}
