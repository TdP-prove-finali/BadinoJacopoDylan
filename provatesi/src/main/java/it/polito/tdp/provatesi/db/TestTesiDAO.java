package it.polito.tdp.provatesi.db;

import java.util.List;

import it.polito.tdp.model.Quotazione;
import it.polito.tdp.model.Statistica;

public class TestTesiDAO {

	public static void main(String[] args) {
		TesiDAO dao = new TesiDAO();
		List<Quotazione> res1 = dao.getAllQuotazioni();
		for (Quotazione q : res1)
			System.out.println(q.toString());
		List<Statistica> res2 = dao.getAllStats();
		for (Statistica s : res2)
			System.out.println(s.toString());
		
	}
}
