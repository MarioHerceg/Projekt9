package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * Koristi se kao klasa ustanove Fakulteta Racunarstva
 * 
 * @author Mario
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5057210194876688909L;

	/**
	 * Kontruktor klase FakultetRacunarstva
	 * 
	 * @param nazivUstanove predstavlja naziv ustanove
	 * @param studs      predstavlja polje studenata
	 * @param preds      predstavlja polje predmeta
	 * @param profs     predstavlja polje profesora
	 * @param ispits        predstavlja polje ispitnih rokova
	 * @see Student
	 * @see Predmet
	 * @see Profesor
	 * @see Ispit
	 */
	public FakultetRacunarstva(String nazivUstanove, List<Student> studs, List<Predmet> preds, List<Profesor> profs,
			List<Ispit> ispits, Long id) {
		super(nazivUstanove, studs, preds, profs, ispits, id);
		// TODO Auto-generated constructor stub
	}

	private static final int BR_STUD = 2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.java.vjezbe.entitet.Visokoskolska#izracunajKonacnuOcjenuStudijaZaStudenta(
	 * hr.java.vjezbe.entitet.Ispit[], java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> is, Integer ocjenaPismenog,
			Integer ocjenaObrane) {
		BigDecimal konacnaOcjena = new BigDecimal("0");
		BigDecimal tri = new BigDecimal("3");
		BigDecimal oc = new BigDecimal(ocjenaPismenog);
		BigDecimal ocd = new BigDecimal(ocjenaObrane);
		BigDecimal pet = new BigDecimal("5");
		konacnaOcjena = konacnaOcjena.add(odrediProsjekOcjenaNaIspitima(is));
		konacnaOcjena = konacnaOcjena.multiply(tri);
		konacnaOcjena = konacnaOcjena.add(oc);
		konacnaOcjena = konacnaOcjena.add(ocd);
		konacnaOcjena = konacnaOcjena.divide(pet);
		return konacnaOcjena;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Diplomski#odrediStudentaZaRektorovuNagradu()
	 */
	@Override
	public Student odrediStudentaZaRektorovuNagradu() {
		BigDecimal best = new BigDecimal("0");
		BigDecimal jedan = new BigDecimal("1");
		Student s3 = null;
		Student[] s4 = new Student[BR_STUD];
		Integer k = 0;
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

			if (res == 1) {
				best = pros;
				s3 = s2;
			} else if (res == 0) {
				if (s2.getDatumRodjenja().getYear() > s3.getDatumRodjenja().getYear()) {
					best = pros;
					s3 = s2;
				} else if (s2.getDatumRodjenja().getYear() == s3.getDatumRodjenja().getYear()
						&& s2.getDatumRodjenja().getMonthValue() > s3.getDatumRodjenja().getMonthValue()) {
					best = pros;
					s3 = s2;
				} else if (s2.getDatumRodjenja().getMonthValue() == s3.getDatumRodjenja().getMonthValue()
						&& s2.getDatumRodjenja().getDayOfMonth() > s3.getDatumRodjenja().getDayOfMonth()) {
					best = pros;
					s3 = s2;
				} else if (s2.getDatumRodjenja().getDayOfMonth() == s3.getDatumRodjenja().getDayOfMonth()) {
					s4[k] = s3;
					k++;
					s4[k] = s2;
					k++;
					best = pros;
					s3 = s2;
				}
			}
		}
		try {
			if (s4[0] != null)
				throw new PostojiViseNajmladjihStudenataException(
						"Pronaðeno je više najmlaðih studenata s istim datumom roðenja, a to su ");
		} catch (PostojiViseNajmladjihStudenataException e) {
			System.out.print(e.getMessage());
			for (int l = 0; l < k; l++) {
				System.out.print(s4[l].getIme() + " " + s4[l].getPrezime() + " i ");
			}
			System.out.println(" ");
		}
		return s3;
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
		Student najStud = null;
		Integer sum = 0;
		for (Student s : getStudenti()) {
			Integer j = 0;
			for (Ispit i : getIspiti()) {

				if (i.getOcjena() == Ocjena.izvrstan.getOcjena() && i.getStudenti() == s) {
					j++;
				}
			}
			if (j > sum)
				najStud = s;
		}
		return najStud;
	}

}
