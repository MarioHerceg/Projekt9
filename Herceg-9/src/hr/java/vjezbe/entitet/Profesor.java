package hr.java.vjezbe.entitet;

/**
 * Koristi se kao klasa Profesora
 * @author Mario 
 */
public class Profesor extends Osoba {
	private String sifra;
	private String ime;
	private String prezime;
	private String titula;
	private Long id;

	/**
	 * Konstruktor klase Profesor
	 * 
	 * @param sifra   predstavlja sifru profesora
	 * @param ime     predstavlja ime profesora
	 * @param prezime predstavlja prezime profesora
	 * @param titula  predstavlja titulu profesora
	 */
	public Profesor(String sifra, String ime, String prezime, String titula, Long id) {
		super(ime, prezime,id);
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.titula = titula;
		this.id = id;
	}

	/**
	 * @return vra�a �ifru profesora
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * Postavlja �ifru profesora
	 * 
	 * @param sifra predstavlja �ifru profesora
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**
	 * @return vra�a ime profesora
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime profesora
	 * 
	 * @param ime predstavlja ime profesora
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * @return vra�a prezime profesora
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja prezime profesora
	 * 
	 * @param prezime predstavlja prezime profesora
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * @return vra�a titulu profesora
	 */
	public String getTitula() {
		return titula;
	}

	/**
	 * Postavlja titulu profesora
	 * 
	 * @param titula predstavlja titulu profesora
	 */
	public void setTitula(String titula) {
		this.titula = titula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
