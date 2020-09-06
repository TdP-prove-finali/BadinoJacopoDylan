package it.polito.tdp.model;

public class GiocatoreVotoQuotaz {
	int id;
	String ruolo;
	String nome;
	String squadra;
	int quotazione;
	float fm;
	float mv;

	public GiocatoreVotoQuotaz(int id, String ruolo, String nome, String squadra, int quotazione, float fm, float mv) {
		super();
		this.id = id;
		this.ruolo = ruolo;
		this.nome = nome;
		this.squadra = squadra;
		this.quotazione = quotazione;
		this.fm = fm;
		this.mv = mv;
	}

	public int getId() {
		return id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public String getNome() {
		return nome;
	}

	public String getSquadra() {
		return squadra;
	}

	public int getQuotazione() {
		return quotazione;
	}

	public float getFm() {
		return fm;
	}

	public float getMv() {
		return mv;
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
		GiocatoreVotoQuotaz other = (GiocatoreVotoQuotaz) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
