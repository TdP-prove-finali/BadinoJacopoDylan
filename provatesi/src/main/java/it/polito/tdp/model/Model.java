package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.provatesi.db.TesiDAO;

public class Model {

	TesiDAO dao = new TesiDAO();
	Map<Integer, Quotazione> databasePlayer;
	Map<Integer, Statistica> databaseStats;

	public Model() {
		dao = new TesiDAO();
		databasePlayer = new HashMap<Integer, Quotazione>();
		databaseStats = new HashMap<Integer, Statistica>();

		for (Quotazione q : dao.getAllQuotazioni())
			databasePlayer.put(q.id, q);

		for (Statistica s : dao.getAllStats())
			databaseStats.put(s.id, s);
	}

	public List<String> getAllSquadre() {
		List<String> res = new ArrayList<String>();
		for (Quotazione q : databasePlayer.values())
			if (res.contains(q.team) == false)
				res.add(q.team);

		Collections.sort(res);
		return res;
	}

	public Statistica calcolaTopPlayer(String team) {
		Statistica best = null;
		for (Statistica s : databaseStats.values())
			if (s.team.equals(team)) {
				if (best == null && s.pg >= 19)
					best = s;
				else if (best != null && s.mf > best.mf && s.pg >= 19)
					best = s;
			}
		return best;
	}

	public Statistica calcolaAssistMan(String team) {
		Statistica best = null;
		for (Statistica s : databaseStats.values())
			if (s.team.equals(team)) {
				if (best == null && s.pg >= 19)
					best = s;
				else if (best != null && (s.Asf + s.Ass) > (best.Asf + best.Ass) && s.pg >= 19)
					best = s;
			}
		return best;
	}

	public Statistica calcolaMuro(String team) {
		Statistica best = null;
		for (Statistica s : databaseStats.values())
			if (s.team.equals(team)) {
				if (best == null && s.pg >= 19 && s.role.equals("D"))
					best = s;
				else if (best != null && s.pg >= 19 && s.role.equals("D") && s.mv > best.mv)
					best = s;
			}
		return best;
	}

	public Statistica calcolaFlop(String team) {
		Statistica best = null;
		for (Statistica s : databaseStats.values())
			if (s.team.equals(team)) {
				if (best == null && s.pg >= 19 && s.role.equals("P") == false)
					best = s;
				else if (best != null && s.pg >= 19 && s.role.equals("P") == false && s.mf < best.mf)
					best = s;
			}
		return best;
	}

	public Quotazione calcolaSorpresa(String team) {
		Statistica best = null;
		for (Statistica s : databaseStats.values())
			if (s.team.equals(team)) {
				// !!!
				if (best == null && s.pg >= 19 && s.mf > 6)
					best = s;
				else if (best != null && s.pg >= 19 && databasePlayer.get(s.getId()) != null && s.mf > 6) {
					Float indicePlayer = s.getMf() / databasePlayer.get(s.getId()).getQtI();
					Float indiceBest = best.getMf() / databasePlayer.get(best.getId()).getQtI();
					if (indicePlayer > indiceBest)
						best = s;
				}
			}
		return databasePlayer.get(best.getId());
	}

	List<GiocatoreVotoQuotaz> soluzione;
	int budget;
	int p;
	int d;
	int c;
	int a;
	int pAtt;
	int dAtt;
	int cAtt;
	int aAtt;

	public List<GiocatoreVotoQuotaz> calcolaBestMF(int p, int d, int c, int a, int crediti) {
		soluzione = new ArrayList<GiocatoreVotoQuotaz>();
		budget = crediti;
		this.p = p;
		this.d = d;
		this.c = c;
		this.a = a;
		pAtt = 0;
		dAtt = 0;
		cAtt = 0;
		aAtt = 0;

		ricorsione(new ArrayList<GiocatoreVotoQuotaz>(), budget);

		return soluzione;
	}

	public void ricorsione(List<GiocatoreVotoQuotaz> parziale, int creditiRim) {

		if (pAtt == p && dAtt == d && cAtt == c && aAtt == a) {
			if (soluzione.size() == 0 || calcolaTotalFantaMedia(soluzione) < calcolaTotalFantaMedia(parziale)) {
				soluzione.clear();
				soluzione.addAll(parziale);
			}
		}

		for (Quotazione q : databasePlayer.values())
			if (databaseStats.get(q.getId()) != null && databaseStats.get(q.getId()).getPg() > 18
					&& creditiRim >= q.getQtA()) {

				GiocatoreVotoQuotaz gvq = new GiocatoreVotoQuotaz(q.getId(), q.getRole(), q.getName(), q.getTeam(),
						q.getQtA(), databaseStats.get(q.getId()).getMf(), databaseStats.get(q.getId()).getMv());

				if (parziale.contains(gvq) == false) {
					if (gvq.getRuolo().equals("P") && pAtt < p) {

						parziale.add(gvq);
						pAtt++;
						ricorsione(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						pAtt--;
					}
					if (gvq.getRuolo().equals("D") && dAtt < d) {

						parziale.add(gvq);
						dAtt++;
						ricorsione(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						dAtt--;
					}
					if (gvq.getRuolo().equals("C") && cAtt < c) {

						parziale.add(gvq);
						cAtt++;
						ricorsione(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						cAtt--;

					}
					if (gvq.getRuolo().equals("A") && aAtt < a) {

						parziale.add(gvq);
						aAtt++;
						ricorsione(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						aAtt--;

					}
				}
			}

	}

	public List<GiocatoreVotoQuotaz> calcolaBestMV(int p, int d, int c, int a, int crediti) {
		soluzione = new ArrayList<GiocatoreVotoQuotaz>();
		budget = crediti;
		this.p = p;
		this.d = d;
		this.c = c;
		this.a = a;
		pAtt = 0;
		dAtt = 0;
		cAtt = 0;
		aAtt = 0;

		ricorsione2(new ArrayList<GiocatoreVotoQuotaz>(), budget);

		return soluzione;
	}

	public void ricorsione2(List<GiocatoreVotoQuotaz> parziale, int creditiRim) {

		if (pAtt == p && dAtt == d && cAtt == c && aAtt == a) {
			if (soluzione.size() == 0 || calcolaTotalMedia(soluzione) < calcolaTotalMedia(parziale)) {
				soluzione.clear();
				soluzione.addAll(parziale);
			}
		}

		for (Quotazione q : databasePlayer.values())
			if (databaseStats.get(q.getId()) != null && databaseStats.get(q.getId()).getPg() > 18
					&& creditiRim >= q.getQtA()) {

				GiocatoreVotoQuotaz gvq = new GiocatoreVotoQuotaz(q.getId(), q.getRole(), q.getName(), q.getTeam(),
						q.getQtA(), databaseStats.get(q.getId()).getMf(), databaseStats.get(q.getId()).getMv());

				if (parziale.contains(gvq) == false) {
					if (gvq.getRuolo().equals("P") && pAtt < p) {

						parziale.add(gvq);
						pAtt++;
						ricorsione2(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						pAtt--;
					}
					if (gvq.getRuolo().equals("D") && dAtt < d) {

						parziale.add(gvq);
						dAtt++;
						ricorsione2(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						dAtt--;
					}
					if (gvq.getRuolo().equals("C") && cAtt < c) {

						parziale.add(gvq);
						cAtt++;
						ricorsione2(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						cAtt--;

					}
					if (gvq.getRuolo().equals("A") && aAtt < a) {

						parziale.add(gvq);
						aAtt++;
						ricorsione2(parziale, creditiRim - gvq.getQuotazione());
						parziale.remove(gvq);
						aAtt--;

					}
				}
			}

	}

	public int calcolaTotalQuot(List<GiocatoreVotoQuotaz> parz) {
		int tot = 0;
		for (GiocatoreVotoQuotaz gvq : parz)
			tot += gvq.getQuotazione();

		return tot;

	}

	public float calcolaTotalFantaMedia(List<GiocatoreVotoQuotaz> parz) {
		float tot = 0;
		for (GiocatoreVotoQuotaz gvq : parz)
			tot += gvq.getFm();

		return tot;

	}

	public float calcolaTotalMedia(List<GiocatoreVotoQuotaz> parz) {
		float tot = 0;
		for (GiocatoreVotoQuotaz gvq : parz)
			tot += gvq.getMv();

		return tot;

	}

}
