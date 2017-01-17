package springboot.model;

public class Studente {
	
	private int id;
	private String nome;
	private String cognome;
	private String esame;
	
	public Studente() {
	}

	public Studente(int id, String nome, String cognome, String esame) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.esame = esame;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getEsame() {
		return esame;
	}

	public void setEsame(String esame) {
		this.esame = esame;
	}
	
	

}
