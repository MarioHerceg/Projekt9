package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Koristi se kao suèelje koje æe naslijediti FakultetRacunarstva i
 * VeleucilisteJave
 * 
 * @author Mario
 */
public interface Visokoskolska {
	/**
	 * Izraèunava konaènu ocjenu studija za studenta
	 * 
	 * @param ispitStudenta  predstavlja polje ispita
	 * @param ocjenaPismenog predstavlja ocjenu pismenog dijela ispita
	 * @param ocjenaObrane   prestavlja ocjenu obrane završnog rada
	 * @return vraæa konaènu ocjenu studija za studenta tipa BigDecimal
	 * @see Ispit
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispitStudenta, Integer ocjenaPismenog,
			Integer ocjenaObrane);

	/**
	 * Odreðuje prosjek ocjena na ispitima
	 * 
	 * @param is predstavlja polje ispita
	 * @return prosjek ocjena tipa BigDecimal
	 */
	default public BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> is) {
		BigDecimal sum = new BigDecimal("0");
		BigDecimal k = new BigDecimal("0");
		BigDecimal jedan = new BigDecimal("1");
		for (Ispit i : is) {
			if (i != null) {
				BigDecimal oc = new BigDecimal(i.getOcjena());
				sum = sum.add(oc);
				k = k.add(jedan);
			}
		}

		sum = sum.divide(k);
		return sum;
	}

	/**
	 * Filtrira ispite tako da nema negativnih ocjena
	 * 
	 * @param is predstavlja polje ispita
	 * @return vraæa polje filtriranih ispita
	 */
	@SuppressWarnings("unused")
	public static Ispit[] filtrirajPolozeneIspite(Ispit[] is) {
		Ispit[] is2 = new Ispit[15];
		Integer j = 0;
		for (Ispit i : is) {
			if (i.getOcjena() != Ocjena.nedovoljan.getOcjena()) {
				is2[j] = i;
				j++;
			}
		}
		return is2;
	}

	/**
	 * Filtrira ispite po studentu
	 * 
	 * @param ispits predstavlja polje ispita
	 * @param st     predstavlja objekt klase Student
	 * @return vraæa polje filtriranih ispita
	 */
	default public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispits, Student st) {

		List<Ispit> is3 = ispits.stream().filter(i -> i.getStudenti().getId().equals(st.getId()))
				.collect(Collectors.toList());

		return is3;
	}
}
