package hr.java.vjezbe.entitet;

/**
 * Koristi se kao klasa Osobe
 * @author Mario 
 *
 */
public class Osoba extends Entitet {
	private String ime;
	private String prezime;
	@SuppressWarnings("unused")
	private Long id;

	/**
	 * Konstruktor klase Osoba
	 * 
	 * @param ime     predstavlja ime osobe
	 * @param prezime predstavlja prezime osobe
	 */
	public Osoba(String ime, String prezime, Long id) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.id = id;
	}

	/**
	 * @return vraæa ime osobe
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime osobe
	 * 
	 * @param ime ime osobe
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * @return vraæa prezime osobe
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * 
	 * @param prezime prezime osobe
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}
