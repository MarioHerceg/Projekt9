package hr.java.vjezbe;

import java.io.IOException;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class UnosStudentiController {
	@FXML
	private TextField spremiJmbag;
	@FXML
	private TextField spremiIme;
	@FXML
	private TextField spremiPrezime;
	@FXML
	private DatePicker spremiDatum;

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
	private void spremi(ActionEvent event) {
		
		boolean error=false;
		String errori="";
		
		if(spremiJmbag.getText().isEmpty() || spremiJmbag.getText()==null) {
			errori=(errori+"Molimo unesite JMBAG!");
			error=true;
		}
		if(spremiIme.getText().isEmpty() || spremiIme.getText()==null) {
			errori=(errori+"\nMolimo unesite ime!");
			error=true;
		}
		if(spremiPrezime.getText().isEmpty() || spremiPrezime.getText()==null) {
			errori=(errori+"\nMolimo unesite prezime!");
			error=true;
		}
		if(spremiDatum.getValue()==null) {
			errori=(errori+"\nMolimo unesite datum roðenja!");
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
			List<Student> studenti = null;
			try {
				studenti = BazaPodataka.dohvatiStudenta();
			} catch (BazaPodatakaException e) {
				e.printStackTrace();
			}

			Long maksimalniId = studenti.stream().mapToLong(stud -> stud.getId()).max().getAsLong();
			
			Student Stud = new Student(spremiIme.getText(), spremiPrezime.getText(), spremiJmbag.getText(),
					spremiDatum.getValue(), maksimalniId);

			try {
				BazaPodataka.spremiNovogStudenta(Stud);
			} catch (BazaPodatakaException e) {
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješan unos!");
			alert.setHeaderText("Uspješan unos!");
			alert.setContentText("Bravo ispravno ste unijeli studenta!");
			
			alert.showAndWait();
		}
		
	}
}
