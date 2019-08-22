package hr.java.vjezbe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

public class IspitiController implements Initializable{
	@FXML
	private TableView<Ispit> tbl;
	@FXML
	private TextField searchNaziv;
	@FXML
	private TextField searchOcjena;
	@FXML
	private TextField searchIme;
	@FXML
	private DatePicker searchDatum;
	@FXML
	private TableColumn<Ispit, String> col_naziv;
	@FXML
	private TableColumn<Ispit, String> col_ocjena;
	@FXML
	private TableColumn<Ispit, String> col_student;
	@FXML
	private TableColumn<Ispit, String> col_dit;
	
	ObservableList<Ispit> list = FXCollections.observableArrayList(ucitajIspita());
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	FilteredList filter = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter2 = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter3 = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter4 = new FilteredList(list, e -> true);
	
	private List<Ispit> ucitajIspita() {
		List<Ispit> ispiti = null;
		try{
			ispiti = BazaPodataka.dohvatiIspit(BazaPodataka.dohvatiPredmeta(BazaPodataka.dohvatiProfesora()),BazaPodataka.dohvatiStudenta());
		}catch (BazaPodatakaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ispiti;
	}
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		col_naziv.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 property.setValue(ispit.getValue().getPredmeti().getNaziv());
				 return property;
				 }
				 });
		col_ocjena.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
			 public ObservableValue<String> call(
			TableColumn.CellDataFeatures<Ispit, String> ispit) {
			 SimpleStringProperty property = new
			SimpleStringProperty();
			 String oc = String.valueOf(ispit.getValue().getOcjena());
			 property.setValue(oc);
			 return property;
			 }
			 });
		col_student.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 String imeIPrez=(ispit.getValue().getStudenti().getIme() + " " + ispit.getValue().getStudenti().getPrezime());
				 property.setValue(imeIPrez);
				 return property;
				 }
				 });
		col_dit.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
				 property.setValue(
				ispit.getValue().getDatumIVrijeme().format(formatter));
				 return property;
				 }
				 });
		tbl.setItems(list);
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@FXML
	private void search(ActionEvent event) {
		filter.setPredicate((Predicate<? super Ispit>) (Ispit rok) -> {
			if (searchNaziv.getText().isEmpty() || searchNaziv.getText() == null) {
				return true;
			} else if (rok.getPredmeti().getNaziv().contains(searchNaziv.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort = new SortedList(filter);
		if (!(sort.equals(list))) {
			sort.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort);
		}
		
		filter2.setPredicate((Predicate<? super Ispit>) (Ispit rok) -> {
			if (searchIme.getText().isEmpty() || searchIme.getText() == null) {
				return true;
			} else if (rok.getStudenti().getIme().contains(searchIme.getText()) || rok.getStudenti().getPrezime().contains(searchIme.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort2 = new SortedList(filter2);
		if (!(sort2.equals(list))) {
			sort2.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort2);
		}
		
		filter3.setPredicate((Predicate<? super Ispit>) (Ispit rok) -> {
			if (searchOcjena.getText().isEmpty() || searchOcjena.getText() == null) {
				return true;
			} else if (String.valueOf(rok.getOcjena()).contains(searchOcjena.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort3 = new SortedList(filter3);
		if (!(sort3.equals(list))) {
			sort3.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort3);
		}
		
		filter4.setPredicate((Predicate<? super Ispit>) (Ispit rok) -> {

			if (searchDatum.getValue() == null) {
				return true;
			} else if (String.valueOf(rok.getDatumIVrijeme()).contains(String.valueOf(searchDatum.getValue()))) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort4 = new SortedList(filter4);
		if (!(sort4.equals(list))) {
			sort4.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort4);
		}
	}
}
