package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProfesoriController implements Initializable {
	@FXML
	private TextField searchSifra;
	@FXML
	private TextField searchIme;
	@FXML
	private TextField searchPrezime;
	@FXML
	private TextField searchTitula;
	@FXML
	private TableView<Profesor> tbl;
	@FXML
	private TableColumn<Profesor, String> col_sifra;
	@FXML
	private TableColumn<Profesor, String> col_prezime;
	@FXML
	private TableColumn<Profesor, String> col_ime;
	@FXML
	private TableColumn<Profesor, String> col_titula;
	
	
	
	ObservableList<Profesor> list = FXCollections.observableArrayList(ucitajProfesora());
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	FilteredList filter = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter2 = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter3 = new FilteredList(list, e -> true);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FilteredList filter4 = new FilteredList(list, e -> true);

	private List<Profesor> ucitajProfesora() {
		List<Profesor> profesori = null;
		try{
			profesori = BazaPodataka.dohvatiProfesora();
		}catch (BazaPodatakaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return profesori;
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
		col_prezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		col_ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
		col_titula.setCellValueFactory(new PropertyValueFactory<>("titula"));
		tbl.setItems(list);

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void search(ActionEvent event) {
		
		
		filter.setPredicate((Predicate<? super Profesor>) (Profesor prof) -> {
			if (searchSifra.getText().isEmpty() || searchSifra.getText() == null) {
				return true;
			} else if (prof.getSifra().contains(searchSifra.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort = new SortedList(filter);
		if(!(sort.equals(list))) {
			sort.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort);
		}
		
		
		filter2.setPredicate((Predicate<? super Profesor>) (Profesor prof) -> {
			if (searchIme.getText().isEmpty() || searchIme.getText() == null) {
				return true;
			} else if (prof.getIme().contains(searchIme.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort2 = new SortedList(filter2);
		if(!(sort2.equals(list))) {
			sort2.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort2);
		}
		
		filter3.setPredicate((Predicate<? super Profesor>) (Profesor prof) -> {
			if (searchPrezime.getText().isEmpty() || searchPrezime.getText() == null) {
				return true;
			} else if (prof.getPrezime().contains(searchPrezime.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort3 = new SortedList(filter3);
		if(!(sort3.equals(list))) {
			sort3.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort3);
		}
		
		filter4.setPredicate((Predicate<? super Profesor>) (Profesor prof) -> {
			if (searchTitula.getText().isEmpty() || searchTitula.getText() == null) {
				return true;
			} else if (prof.getTitula().contains(searchTitula.getText())) {
				return true;
			}
			return false;
		});
		@SuppressWarnings("rawtypes")
		SortedList sort4 = new SortedList(filter4);
		if(!(sort4.equals(list))) {
			sort4.comparatorProperty().bind(tbl.comparatorProperty());
			tbl.setItems(sort4);
		}
	}
}
