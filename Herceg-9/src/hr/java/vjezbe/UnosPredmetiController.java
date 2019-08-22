package hr.java.vjezbe;

import java.io.IOException;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class UnosPredmetiController {
	@FXML
	private TextField spremiSifra;
	@FXML
	private TextField spremiNaziv;
	@FXML
	private TextField spremiEcts;
	@FXML
	private TextField spremiNositelj;


	public void prikaziPretraguProfesora() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("profesori.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguStudenata() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("studenti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguPredmeta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("predmeti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguIspita() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("ispiti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void unosProfesora(ActionEvent event) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("unosProfesori.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void unosStudenta(ActionEvent event) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("unosStudenti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void unosPredmeta(ActionEvent event) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("unosPredmeti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void unosIspita(ActionEvent event) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("unosIspiti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void spremi(ActionEvent event) throws BazaPodatakaException {
		
		boolean error=false;
		String errori="";
		
		if(spremiSifra.getText().isEmpty() || spremiSifra.getText()==null) {
			errori=(errori+"Molimo unesite šifru!");
			error=true;
		}
		if(spremiNaziv.getText().isEmpty() || spremiNaziv.getText()==null) {
			errori=(errori+"\nMolimo unesite Naziv predmeta!");
			error=true;
		}
		if(spremiEcts.getText().isEmpty() || spremiEcts.getText()==null) {
			errori=(errori+"\nMolimo unesite broj ECTS bodova!");
			error=true;
		}
		if(spremiNositelj.getText().isEmpty() || spremiNositelj.getText()==null) {
			errori=(errori+"\nMolimo unesite redni broj nositelja predmeta!");
			error=true;
		}
		if(error==true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pogreška u unosu!");
			alert.setHeaderText("Pogreška u unosu!");
			alert.setContentText(errori);
			

			alert.showAndWait();
		}
		else {
			List<Predmet> predmeti = null;
			try {
				predmeti = BazaPodataka.dohvatiPredmeta(BazaPodataka.dohvatiProfesora());
			} catch (BazaPodatakaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Long maksimalniId = predmeti.stream().mapToLong(stud -> stud.getId()).max().getAsLong();
			
			Predmet pred = new Predmet(spremiSifra.getText(), spremiNaziv.getText(), Integer.parseInt(spremiEcts.getText()),
					BazaPodataka.dohvatiProfesora().get(Integer.parseInt(spremiNositelj.getText())-1), maksimalniId);

			try {
				BazaPodataka.spremiNovogPredmeta(pred);
			} catch (BazaPodatakaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješan unos!");
			alert.setHeaderText("Uspješan unos!");
			alert.setContentText("Bravo ispravno ste unijeli predmet!");
			
			alert.showAndWait();
		}
		
	}
}
