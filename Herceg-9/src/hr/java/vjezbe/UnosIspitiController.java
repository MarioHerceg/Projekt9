package hr.java.vjezbe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class UnosIspitiController {
	@FXML
	private TextField spremiPredmet;
	@FXML
	private TextField spremiStudent;
	@FXML
	private TextField spremiOcjena;
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
	private void spremi(ActionEvent event) throws BazaPodatakaException {
		
		boolean error=false;
		String errori="";
		
		if(spremiPredmet.getText().isEmpty() || spremiPredmet.getText()==null) {
			errori=(errori+"Molimo unesite JMBAG!");
			error=true;
		}
		if(spremiStudent.getText().isEmpty() || spremiStudent.getText()==null) {
			errori=(errori+"\nMolimo unesite ime!");
			error=true;
		}
		if(spremiOcjena.getText().isEmpty() || spremiOcjena.getText()==null) {
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
			List<Ispit> ispiti = null;
			try {
				ispiti = BazaPodataka.dohvatiIspit(BazaPodataka.dohvatiPredmeta(BazaPodataka.dohvatiProfesora()),BazaPodataka.dohvatiStudenta());
			} catch (BazaPodatakaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Long maksimalniId = ispiti.stream().mapToLong(stud -> stud.getId()).max().getAsLong();
			
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			LocalDate datum=spremiDatum.getValue();
			String date=datum.format(formatter);
			String dateTime=(date+"T12:30");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
			LocalDateTime datumIspitaIVrijeme = LocalDateTime.parse(dateTime, formatter2);
			
			Ispit rok = new Ispit(BazaPodataka.dohvatiPredmeta(BazaPodataka.dohvatiProfesora()).get(Integer.parseInt(spremiPredmet.getText())-1), BazaPodataka.dohvatiStudenta().get(Integer.parseInt(spremiStudent.getText())-1), Integer.parseInt(spremiOcjena.getText()),
					datumIspitaIVrijeme, maksimalniId);

			try {
				BazaPodataka.spremiNovogIspita(rok);
			} catch (BazaPodatakaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješan unos!");
			alert.setHeaderText("Uspješan unos!");
			alert.setContentText("Bravo ispravno ste unijeli ispit!");
			
			alert.showAndWait();
		}
		
	}
}
