package it.polito.tdp.provatesi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.GiocatoreVotoQuotaz;
import it.polito.tdp.model.Model;
import it.polito.tdp.model.Quotazione;
import it.polito.tdp.model.Statistica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> squadraChoice;

	@FXML
	private Button calcolaButton;

	@FXML
	private TextField topPlayerTxtField;

	@FXML
	private TextField sorpresaTxtField;

	@FXML
	private TextField assistmanTxtField;

	@FXML
	private TextField muroTxtField;

	@FXML
	private TextField flopTxtField;

	@FXML
	private TextField creditiTxtField;

	@FXML
	private ComboBox<Integer> numPortieri;

	@FXML
	private ComboBox<Integer> numDifensori;

	@FXML
	private ComboBox<Integer> numCentrocampisti;

	@FXML
	private ComboBox<Integer> numAttaccanti;

	@FXML
	private Button MFButton;

	@FXML
	private Button MVButton;

	@FXML
	private TextArea txtFinale;

	@FXML
	void MFAction(ActionEvent event) {
		txtFinale.clear();
		int p;
		int d;
		int c;
		int a;
		int crediti;

		try {
			p = numPortieri.getValue();
			d = numDifensori.getValue();
			c = numCentrocampisti.getValue();
			a = numAttaccanti.getValue();
			crediti = Integer.parseInt(creditiTxtField.getText());

			List<GiocatoreVotoQuotaz> soluzione = model.calcolaBestMF(p, d, c, a, crediti);

			for (GiocatoreVotoQuotaz gvq : soluzione)
				txtFinale.appendText(String.format("%s - %d\n", gvq.getNome(), gvq.getQuotazione()));
		} catch (Exception e) {
			txtFinale.setText("ennoooo");
		}
	}

	@FXML
	void MVAction(ActionEvent event) {
		txtFinale.clear();
		int p;
		int d;
		int c;
		int a;
		int crediti;

		try {
			p = numPortieri.getValue();
			d = numDifensori.getValue();
			c = numCentrocampisti.getValue();
			a = numAttaccanti.getValue();
			crediti = Integer.parseInt(creditiTxtField.getText());

			List<GiocatoreVotoQuotaz> soluzione = model.calcolaBestMV(p, d, c, a, crediti);

			for (GiocatoreVotoQuotaz gvq : soluzione)
				txtFinale.appendText(String.format("%s - %d\n", gvq.getNome(), gvq.getQuotazione()));
		} catch (Exception e) {
			txtFinale.setText("ennoooo");
		}
	}

	@FXML
	void calcolaAction(ActionEvent event) {

		try {
			Statistica topP = model.calcolaTopPlayer(squadraChoice.getValue());
			Statistica assist = model.calcolaAssistMan(squadraChoice.getValue());
			Statistica flop = model.calcolaFlop(squadraChoice.getValue());
			Statistica muro = model.calcolaMuro(squadraChoice.getValue());
			Quotazione sorpresa = model.calcolaSorpresa(squadraChoice.getValue());

			topPlayerTxtField.clear();
			topPlayerTxtField.setText(String.format("%s - MF: %.2f", topP.getName(), topP.getMf()));

			muroTxtField.clear();
			muroTxtField.setText(String.format("%s - MV: %.2f", muro.getName(), muro.getMv()));

			assistmanTxtField.clear();
			assistmanTxtField
					.setText(String.format("%s - %d assist", assist.getName(), (assist.getAsf() + assist.getAss())));

			flopTxtField.clear();
			flopTxtField.setText(String.format("%s - MF: %.2f", flop.getName(), flop.getMf()));

			sorpresaTxtField.clear();
			sorpresaTxtField.setText(String.format("%s - QI: %d crediti", sorpresa.getName(), sorpresa.getQtI()));

		} catch (Exception e) {
			topPlayerTxtField.clear();
			topPlayerTxtField.setText("Selezionare una squadra");

			muroTxtField.clear();
			muroTxtField.setText("Selezionare una squadra");

			assistmanTxtField.clear();
			assistmanTxtField.setText("Selezionare una squadra");

			flopTxtField.clear();
			flopTxtField.setText("Selezionare una squadra");

			sorpresaTxtField.clear();
			sorpresaTxtField.setText("Selezionare una squadra");

		}
	}

	@FXML
	void initialize() {
		assert squadraChoice != null : "fx:id=\"squadraChoice\" was not injected: check your FXML file 'Scene.fxml'.";
		assert calcolaButton != null : "fx:id=\"calcolaButton\" was not injected: check your FXML file 'Scene.fxml'.";
		assert topPlayerTxtField != null : "fx:id=\"topPlayerTxtField\" was not injected: check your FXML file 'Scene.fxml'.";
		assert sorpresaTxtField != null : "fx:id=\"sorpresaTxtField\" was not injected: check your FXML file 'Scene.fxml'.";
		assert assistmanTxtField != null : "fx:id=\"assistmanTxtField\" was not injected: check your FXML file 'Scene.fxml'.";
		assert muroTxtField != null : "fx:id=\"muroTxtField\" was not injected: check your FXML file 'Scene.fxml'.";
		assert flopTxtField != null : "fx:id=\"flopTxtField\" was not injected: check your FXML file 'Scene.fxml'.";
		assert creditiTxtField != null : "fx:id=\"creditiTxtField\" was not injected: check your FXML file 'Scene.fxml'.";
		assert numPortieri != null : "fx:id=\"numPortieri\" was not injected: check your FXML file 'Scene.fxml'.";
		assert numDifensori != null : "fx:id=\"numDifensori\" was not injected: check your FXML file 'Scene.fxml'.";
		assert numCentrocampisti != null : "fx:id=\"numCentrocampisti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert numAttaccanti != null : "fx:id=\"numAttaccanti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert MFButton != null : "fx:id=\"MFButton\" was not injected: check your FXML file 'Scene.fxml'.";
		assert MVButton != null : "fx:id=\"MVButton\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtFinale != null : "fx:id=\"txtFinale\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model2) {
		this.model = model2;

		squadraChoice.getItems().addAll(model.getAllSquadre());
		List<Integer> numeri = new ArrayList<Integer>();
		numeri.add(0);
		numeri.add(1);
		numeri.add(2);
		creditiTxtField.setText("0");
		numPortieri.setValue(0);
		numDifensori.setValue(0);
		numCentrocampisti.setValue(0);
		numAttaccanti.setValue(0);
		numPortieri.getItems().addAll(numeri);
		numDifensori.getItems().addAll(numeri);
		numCentrocampisti.getItems().addAll(numeri);
		numAttaccanti.getItems().addAll(numeri);
	}
}
