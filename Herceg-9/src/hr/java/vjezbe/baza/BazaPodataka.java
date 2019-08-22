package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {
	private static final String DATABASE_FILE = "C:\\Users\\Mario2\\eclipse-workspace\\Herceg-9\\sql\\database.properties";

	private static Connection spajanjeNaBazu() throws SQLException, IOException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;
	}

	public static List<Profesor> dohvatiProfesora() throws BazaPodatakaException {
		List<Profesor> listaProfesora = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			Statement stmt = veza.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROFESOR");
			while (rs.next()) {
				Long id = rs.getLong("ID");
				String ime = rs.getString("IME");
				String prezime = rs.getString("PREZIME");
				String sifra = rs.getString("SIFRA");
				String titula = rs.getString("TITULA");
				Profesor noviProfesor = new Profesor(sifra, ime, prezime, titula, id);
				listaProfesora.add(noviProfesor);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			System.err.println(poruka);
		}

		return listaProfesora;

	}
	public static void spremiNovogProfesora(Profesor profesor) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, profesor.getIme());
			preparedStatement.setString(2, profesor.getPrezime());
			preparedStatement.setString(3, profesor.getSifra());
			preparedStatement.setString(4, profesor.getTitula());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, ex);
		}
	}
	public static List<Student> dohvatiStudenta() throws BazaPodatakaException {
		List<Student> listaStudenta = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			Statement stmt = veza.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");
			while (rs.next()) {
				Long id = rs.getLong("ID");
				String ime = rs.getString("IME");
				String prezime = rs.getString("PREZIME");
				String jmbag = rs.getString("JMBAG");
				String datumRodjenjaString = rs.getString("DATUM_RODJENJA");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);
				Student noviStudent = new Student(ime, prezime, jmbag, datumRodjenja, id);
				listaStudenta.add(noviStudent);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			System.err.println(poruka);
		}

		return listaStudenta;

	}
	public static void spremiNovogStudenta(Student student) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, student.getIme());
			preparedStatement.setString(2, student.getPrezime());
			preparedStatement.setString(3, student.getJmbag());
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate datum=student.getDatumRodjenja();
			String date=datum.format(formatter);
			preparedStatement.setString(4, date);
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, ex);
		}
	}
	public static List<Predmet> dohvatiPredmeta(List<Profesor> profs) throws BazaPodatakaException {
		List<Predmet> listaPredmeta = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			Statement stmt = veza.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PREDMET");
			while (rs.next()) {
				Long id = rs.getLong("ID");
				String sifra = rs.getString("SIFRA");
				String naziv = rs.getString("NAZIV");
				Integer ects = Integer.parseInt(rs.getString("BROJ_ECTS_BODOVA"));
				Profesor nos = profs.get(Integer.parseInt(rs.getString("PROFESOR_ID"))-1);
				Predmet noviPredmet = new Predmet(sifra, naziv, ects, nos, id);
				listaPredmeta.add(noviPredmet);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			System.err.println(poruka);
		}

		return listaPredmeta;

	}
	public static void spremiNovogPredmeta(Predmet predmet) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, predmet.getSifra());
			preparedStatement.setString(2, predmet.getNaziv());
			preparedStatement.setString(3, Integer.toString(predmet.getBrojEctsBodova()));
			preparedStatement.setString(4, Long.toString(predmet.getNositelj().getId()));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, ex);
		}
	}
	
	public static List<Ispit> dohvatiIspit(List<Predmet> preds, List<Student> studs) throws BazaPodatakaException {
		List<Ispit> listaIspita = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			Statement stmt = veza.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ISPIT");
			while (rs.next()) {
				Long id = rs.getLong("ID");
				Predmet pred = preds.get(Integer.parseInt(rs.getString("PREDMET_ID"))-1);
				Student stud = studs.get(Integer.parseInt(rs.getString("STUDENT_ID"))-1);
				Integer ocj = Integer.parseInt(rs.getString("OCJENA"));
				String datumIspita = rs.getString("DATUM_I_VRIJEME");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
				LocalDateTime datumIspitaIVrijeme = LocalDateTime.parse(datumIspita, formatter);
				Ispit noviIspit = new Ispit(pred, stud, ocj, datumIspitaIVrijeme, id);
				listaIspita.add(noviIspit);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			System.err.println(poruka);
		}

		return listaIspita;

	}
	public static void spremiNovogIspita(Ispit ispit) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO Ispit(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, Long.toString(ispit.getPredmeti().getId()));
			preparedStatement.setString(2, Long.toString(ispit.getStudenti().getId()));
			preparedStatement.setString(3, Integer.toString(ispit.getOcjena()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
			LocalDateTime dateTime = ispit.getDatumIVrijeme();
			String formattedDateTime = dateTime.format(formatter);
			preparedStatement.setString(4, formattedDateTime);
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, ex);
		}
	}
}
