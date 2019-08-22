package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Koristi se kao obstraktna klasa obrazovne ustanove
 * 
 * @author Mario
 *
 */
public abstract class ObrazovnaUstanova extends Entitet{
	private String nazivUstanove;
	private List<Student> studenti;
	private List<Predmet> predmeti;
	private List<Profesor> profesori;
	private List<Ispit> ispiti;
	private Integer brojStudenata;
	@SuppressWarnings("unused")
	private Long id;

	/**
	 * Konstruktor klase ObrazovnaUstanova
	 * 
	 * @param nazivUstanove predstavlja naziv ustanove
	 * @param studs      prestavlja polje studenata
	 * @param preds      prestavlja polje predmeta
	 * @param profs     predstavlja polje profesora
	 * @param ispits        predstavlja polje ispita
	 * @see Student
	 * @see Predmet
	 * @see Profesor
	 * @see Ispit
	 */
	public ObrazovnaUstanova(String nazivUstanove, List<Student> studs, List<Predmet> preds, List<Profesor> profs,
			List<Ispit> ispits, Long id) {
		super(id);
		this.nazivUstanove = nazivUstanove;
		this.studenti = studs;
		this.predmeti = preds;
		this.profesori = profs;
		this.ispiti = ispits;
		this.brojStudenata = studs.size();
		this.id = id;
	}

	/**
	 * @return vraæa naziv ustanove
	 */
	public String getNazivUstanove() {
		return nazivUstanove;
	}

	/**
	 * Postavlja naziv ustanove
	 * 
	 * @param nazivUstanove predstavlja naziv ustanove
	 */
	public void setNazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}

	/**
	 * @return vraæa pohranjenog studenta
	 */
	public List<Student> getStudenti() {
		return studenti;
	}
	public int getBrojStudenata() {
		return brojStudenata;
	}
	/**
	 * Postavlja polje studenata
	 * 
	 * @param studenti prestavlja polje studenata
	 */
	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	/**
	 * @return vraæa pohranjenog predmeta
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}
	/**
	 * Postavlja polje predmeta
	 * 
	 * @param predmeti predstavlja polje predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	/**
	 * @return vraæa pohranjenog profesora
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * Postavlja polje profesora
	 * 
	 * @param profesori predstavlja polje profesora
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	/**
	 * @return vraæa pohranjenog ispita
	 */
	public List<Ispit> getIspiti() {
		return ispiti;
	}

	/**
	 * Postavlja polje ispita
	 * 
	 * @param ispiti predstavlja polje ispita
	 */
	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

	/**
	 * Odreðuje najuspješnijeg studenta na godini
	 * 
	 * @param cijeliBroj prestavlja upisanu godinu
	 * @re
	 * turn vraæa objekt klase Student
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer cijeliBroj);
}
