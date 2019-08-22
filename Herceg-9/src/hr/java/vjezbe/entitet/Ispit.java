package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Koristi se kao klasa Ispit
 * 
 * @author Mario
 *
 */
public class Ispit extends Entitet{
	private Predmet predmeti;
	private Student studenti;
	private Integer ocjena;
	private LocalDateTime datumIVrijeme;

	/**
	 * Konstruktor klase Ispit
	 * 
	 * @param predmeti      objekt klase Predmet
	 * @param studenti      objekt klase Student
	 * @param ocjena        objekt klase Student
	 * @param datumIVrijeme lokalno vrijeme
	 * @see Predmet
	 * @see Student
	 */
	public Ispit(Predmet predmeti, Student studenti, Integer ocjena, LocalDateTime datumIVrijeme, Long id) {
		super(id);
		this.predmeti = predmeti;
		this.studenti = studenti;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}

	/**
	 * @return vraæa pohranjeni objekt klase Predmet
	 */
	public Predmet getPredmeti() {
		return predmeti;
	}

	/**
	 * Postavlja objekt klase Predmet
	 * 
	 * @param predmeti objekt klase Predmet
	 */
	public void setPredmeti(Predmet predmeti) {
		this.predmeti = predmeti;
	}

	/**
	 * @return vraæa pohranjeni objekt klase Student
	 */
	public Student getStudenti() {
		return studenti;
	}

	/**
	 * Postavlja objekt klase Student
	 * 
	 * @param studenti objekt klase Student
	 */
	public void setStudenti(Student studenti) {
		this.studenti = studenti;
	}

	/**
	 * @return vraæa ocjenu ispitnog roka
	 */
	public Integer getOcjena() {
		return ocjena;
	}

	/**
	 * Postavlja ocjenu ispitnog roka
	 * 
	 * @param ocjena ocjena ispitnog roka
	 */
	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}

	/**
	 * @return vraæa pohranjeni datum i vrijeme
	 */
	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}

	/**
	 * Postavlja odreðeni datum i vrijeme
	 * 
	 * @param datumIVrijeme objekt klase LocalDateTime
	 */
	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}

}
