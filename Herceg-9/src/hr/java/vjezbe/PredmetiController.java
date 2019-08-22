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
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;

public class PredmetiController implements Initializable{
	@FXML
	private TableView<Predmet> tbl;
	@FXML
	private TextField searchSifra;
	@FXML
	private TextField searchNaziv;
	@FXML
	private TextField searchEcts;
	@FXML
	private TextField searchNositelj;
	@FXML
	private TableColumn<Predmet, String> col_sifra;
	@FXML
	private TableColumn<Predmet, String> col_naziv;
	@FXML
	private TableColumn<Predmet, String> col_ects;
	@FXML
	private TableColumn<Predmet, String> col_prof;
	
	ObservableList<Predmet> list = FXCollections.observableArrayList(ucitajPredmeta());
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	FilteredList filter = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter2 = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter3 = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter4 = new FilteredList(list, e -> true);
	
	private List<Predmet> ucitajPredmeta() {
		List<Predmet> predmeti = null;
		try{
			predmeti = BazaPodataka.dohvatiPredmeta(BazaPodataka.dohvatiProfesora());
		}catch (BazaPodatakaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return predmeti;
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
		col_sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		col_naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		col_ects.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Predmet, String>,
				ObservableValue<String>>() {
			 public ObservableValue<String> call(
			TableColumn.CellDataFeatures<Predmet, String> predmet) {
			 SimpleStringProperty property = new
			SimpleStringProperty();
			 String ects = String.valueOf(predmet.getValue().getBrojEctsBodova());
			 property.setValue(ects);
			 return property;
			 }
			 });
		col_prof.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>,
				ObservableValue<String>>() {
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Predmet, String> predmet) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 String imeIPrez=(predmet.getValue().getNositelj().getIme()+" "+ predmet.getValue().getNositelj().getPrezime());
				 property.setValue(imeIPrez);
				 return property;
				 }
				 });

		tbl.setItems(list);
		
	}
	
	@SuppressWarnings({ "unchecked"})
	@FXML
	private void search(ActionEvent event) {
		filter.setPredicate((Predicate<? super Predmet>) (Predmet pred) -> {
			if (searchSifra.getText().isEmpty() || searchSifra.getText() == null) {
				return true;
			} else if (pred.getSifra().contains(searchSifra.getText())) {
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
		
		filter2.setPredicate((Predicate<? super Predmet>) (Predmet pred) -> {
			if (searchNaziv.getText().isEmpty() || searchNaziv.getText() == null) {
				return true;
			} else if (pred.getNaziv().contains(searchNaziv.getText())) {
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
		
		filter3.setPredicate((Predicate<? super Predmet>) (Predmet pred) -> {
			if (searchEcts.getText().isEmpty() || searchEcts.getText() == null) {
				return true;
			} else if (String.valueOf(pred.getBrojEctsBodova()).contains(searchEcts.getText())) {
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
		
		filter4.setPredicate((Predicate<? super Predmet>) (Predmet pred) -> {
			if (searchNositelj.getText().isEmpty() || searchNositelj.getText() == null) {
				return true;
			} else if (pred.getNositelj().getIme().contains(searchNositelj.getText()) || pred.getNositelj().getPrezime().contains(searchNositelj.getText())) {
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
