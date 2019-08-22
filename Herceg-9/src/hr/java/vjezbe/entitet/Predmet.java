package hr.java.vjezbe.entitet;

import java.util.HashSet;
import java.util.Set;

/**
 * Koristi se kao klasa Predmeta
 * 
 * @author Mario
 *
 */
public class Predmet extends Entitet{
	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	Set<Student> studs = new HashSet<>();
	@SuppressWarnings("unused")
	private Long id;

	/**
	 * Konstruktor klase Predmet
	 * 
	 * @param sifra          predstavlja �ifru predmeta
	 * @param naziv          predstavlja naziv predmeta
	 * @param brojEctsBodova predstavlja broj ECTS bodova predmeta
	 * @param nositelj       predstavlja nositelja(profesora) predmeta
	 * @param studenti       predstavlja broj studenata na predmetu
	 * @see Profesor
	 */
	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, Long id) {
		super(id);
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.id = id;
	}

	/**
	 * @return vra�a �ifru predmeta
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * Postavlja �ifru predmeta
	 * 
	 * @param sifra predstavlja �ifru predmeta
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**
	 * @return vra�a naziv predmeta
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv predmeta
	 * 
	 * @param naziv predstavlja naziv predmeta
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * @return vra�a broj ECTS bodova predmeta
	 */
	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}

	/**
	 * Postavlaj broj ECTS bodova predmeta
	 * 
	 * @param brojEctsBodova predstavlja broj ECTS bodova predmeta
	 */
	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}

	/**
	 * @return vra�a nositelja(profesora) predmeta
	 */
	public Profesor getNositelj() {
		return nositelj;
	}

	/**
	 * Postavlja nositelja predmeta
	 * 
	 * @param nositelj predstavlja nositelja predmeta tipa Profesor
	 */
	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}

	public Set<Student> getStuds() {
		return studs;
	}

	public void setStuds(Set<Student> studs) {
		this.studs = studs;
	}
	
}
