package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Koristi se kao klasa Studenta
 * 
 * @author Mario
 *
 */
public class Student extends Osoba {
	private String ime;
	private String prezime;
	private String jmbag;
	private LocalDate datumRodjenja;
	@SuppressWarnings("unused")
	private Long id;

	/**
	 * Konstruktor klase Student
	 * 
	 * @param ime           predstavlja ime studenta
	 * @param prezime       predstavlja prezime studenta
	 * @param jmbag         predstavlja JMBAG studenta
	 * @param datumRodjenja predstavlja datum roðenja studenta
	 * @param l 
	 */
	public Student(String ime, String prezime, String jmbag, LocalDate datumRodjenja, long id) {
		super(ime, prezime, id);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbag = jmbag;
		this.datumRodjenja = datumRodjenja;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#getIme()
	 */
	public String getIme() {
		return ime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#setIme(java.lang.String)
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#getPrezime()
	 */
	public String getPrezime() {
		return prezime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#setPrezime(java.lang.String)
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * @return vraæa JMBAG studenta
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * Postavlja JMBAG studenta
	 * 
	 * @param jmbag predstavlja JMBAG studenta
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

	/**
	 * @return vraæa datum roðenja studenta
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	/**
	 * Postavlja datum roðenja studenta
	 * 
	 * @param datumRodjenja predstavlja datum roðenja studenta
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodjenja == null) ? 0 : datumRodjenja.hashCode());
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (datumRodjenja == null) {
			if (other.datumRodjenja != null)
				return false;
		} else if (!datumRodjenja.equals(other.datumRodjenja))
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		return true;
	}

}
