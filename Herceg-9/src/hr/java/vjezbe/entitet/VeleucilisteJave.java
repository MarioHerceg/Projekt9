package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

/**
 * Koristi se kao klasa ustanove Veleucilista jave
 * 
 * @author Mario 
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8659449944923198941L;

	/**
	 * Konstruktor klase Veleucilista Jave
	 * 
	 * @param nazivUstanove prestavlja naziv ustanove
	 * @param studs      predstavlja polje studenata
	 * @param preds      predstavlja polje predmeta
	 * @param profs     predstavlja polje profesora
	 * @param ispits        predstavlja polje ispita
	 * @see Student
	 * @see Predmet
	 * @see Profesor 
	 * @see Ispit
	 */
	public VeleucilisteJave(String nazivUstanove, List<Student> studs, List<Predmet> preds, List<Profesor> profs,
			List<Ispit> ispits, Long id) {
		super(nazivUstanove, studs, preds, profs, ispits, id);
		// TODO Auto-generated constructor stub
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.java.vjezbe.entitet.Visokoskolska#izracunajKonacnuOcjenuStudijaZaStudenta(
	 * hr.java.vjezbe.entitet.Ispit[], java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispitStudenta, Integer ocjenaPismenog,
			Integer ocjenaObrane) {
		BigDecimal konacnaOcjena = new BigDecimal("0");
		BigDecimal dva = new BigDecimal("2");
		BigDecimal oc = new BigDecimal(ocjenaPismenog);
		BigDecimal ocb = new BigDecimal(ocjenaObrane);
		BigDecimal cetiri = new BigDecimal("4");
		konacnaOcjena = konacnaOcjena.add(odrediProsjekOcjenaNaIspitima(ispitStudenta));
		konacnaOcjena = konacnaOcjena.multiply(dva);
		konacnaOcjena = konacnaOcjena.add(oc);
		konacnaOcjena = konacnaOcjena.add(ocb);
		konacnaOcjena = konacnaOcjena.divide(cetiri);
		return konacnaOcjena;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.java.vjezbe.entitet.ObrazovnaUstanova#odrediNajuspjesnijegStudentaNaGodini
	 * (java.lang.Integer)
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(Integer cijeliBroj) {
		BigDecimal best = new BigDecimal("0");
		BigDecimal jedan = new BigDecimal("1");
		Student s3 = null;
		for (Student s2 : getStudenti()) {
			List<Ispit> is = filtrirajIspitePoStudentu(getIspiti(), s2);
			BigDecimal pros = null;
			try {
				pros = odrediProsjekOcjenaNaIspitima(is);
				for (Ispit i : is) {
					if (i != null) {
						if (i.getOcjena() == Ocjena.nedovoljan.getOcjena()) {
							pros = jedan;
							throw new NemoguceOdreditiProsjekStudentaException("Student " + s2.getIme() + " "
									+ s2.getPrezime()
									+ " zbog negativne ocjene na jednom od ispita ima prosjek „nedovoljan (1)“!");
						}
					}
				}
			} catch (NemoguceOdreditiProsjekStudentaException e) {
				System.out.println(e.getMessage());
			}
			int res;
			res = pros.compareTo(best);
			if (res == 0 || res == 1) {
				best = pros;
				s3 = s2;
			}
		}
		return s3;
	}

}
