package it.polito.tdp.provatesi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.model.Quotazione;
import it.polito.tdp.model.Statistica;

public class TesiDAO {

	public List<Quotazione> getAllQuotazioni() {
		final String sql = "SELECT * FROM quotazioni";

		List<Quotazione> quotazioni = new LinkedList<Quotazione>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				quotazioni.add(new Quotazione(res.getInt("Id"), res.getString("R"), res.getString("Nome"),
						res.getString("Squadra"), res.getInt("QtA"), res.getInt("QtI"), res.getInt("Diff")));
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return quotazioni;
	}

	public List<Statistica> getAllStats() {
		final String sql = "SELECT * FROM statistiche";

		List<Statistica> stats = new LinkedList<Statistica>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				stats.add(new Statistica(res.getInt("Id"), res.getString("R"), res.getString("Nome"), res.getString("Squadra"),
						res.getInt("Pg"), res.getFloat("Mv"), res.getFloat("Mf"),res.getInt("Gf"), res.getInt("Gs"),res.getInt("Rp"),res.getInt("Rc"),res.getInt("R+"),res.getInt("R-"),res.getInt("Ass"),res.getInt("Asf"),res.getInt("Amm"),res.getInt("Esp"),res.getInt("Au")));
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return stats;
	}
}
