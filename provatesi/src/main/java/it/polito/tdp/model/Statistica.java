package it.polito.tdp.model;

public class Statistica {
	int id;
	String role;
	String name;
	String team;
	int pg;
	float mv;
	float mf;
	int Gf;
	int Gs;
	int Rp;
	int Rc;
	int RPlus;
	int RMinus;
	int Ass;
	int Asf;
	int Amm;
	int Esp;
	int Au;

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

	public int getPg() {
		return pg;
	}

	public float getMv() {
		return mv;
	}

	public float getMf() {
		return mf;
	}

	public int getGf() {
		return Gf;
	}

	public int getGs() {
		return Gs;
	}

	public int getRp() {
		return Rp;
	}

	public int getRc() {
		return Rc;
	}

	public int getRPlus() {
		return RPlus;
	}

	public int getRMinus() {
		return RMinus;
	}

	public int getAss() {
		return Ass;
	}

	public int getAsf() {
		return Asf;
	}

	public int getAmm() {
		return Amm;
	}

	public int getEsp() {
		return Esp;
	}

	public int getAu() {
		return Au;
	}

	public Statistica(int id, String role, String name, String team, int pg, float mv, float mf, int gf, int gs, int rp,
			int rc, int rPlus, int rMinus, int ass, int asf, int amm, int esp, int au) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.team = team;
		this.pg = pg;
		this.mv = mv;
		this.mf = mf;
		Gf = gf;
		Gs = gs;
		Rp = rp;
		Rc = rc;
		RPlus = rPlus;
		RMinus = rMinus;
		Ass = ass;
		Asf = asf;
		Amm = amm;
		Esp = esp;
		Au = au;
	}

	@Override
	public String toString() {
		return "Statistica [id=" + id + ", role=" + role + ", name=" + name + ", team=" + team + ", pg=" + pg + ", mv="
				+ mv + ", mf=" + mf + ", Gf=" + Gf + ", Gs=" + Gs + ", Rp=" + Rp + ", Rc=" + Rc + ", RPlus=" + RPlus
				+ ", RMinus=" + RMinus + ", Ass=" + Ass + ", Asf=" + Asf + ", Amm=" + Amm + ", Esp=" + Esp + ", Au="
				+ Au + "]";
	}

}
