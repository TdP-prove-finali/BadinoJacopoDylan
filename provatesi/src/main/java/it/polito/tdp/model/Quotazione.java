package it.polito.tdp.model;

public class Quotazione {
	int id;
	String role;
	String name;
	String team;
	int QtA;
	int QtI;
	int diff;

	public Quotazione(int id, String role, String name, String team, int qtA, int qtI, int diff) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.team = team;
		QtA = qtA;
		QtI = qtI;
		this.diff = diff;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public int getQtA() {
		return QtA;
	}

	public int getQtI() {
		return QtI;
	}

	public int getDiff() {
		return diff;
	}

	@Override
	public String toString() {
		return "Quotazione [id=" + id + ", role=" + role + ", name=" + name + ", team=" + team + ", QtA=" + QtA
				+ ", QtI=" + QtI + ", diff=" + diff + "]";
	}
	
	

}
