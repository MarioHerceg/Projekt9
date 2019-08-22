package hr.java.vjezbe.entitet;

/**
 * Predstavlja suèelje Diplomski koja nassljeðuje Visokoskolska
 * @author Mario
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Odreðuje najboljeg studenta godine
	 * @return objekt klase Student
	 * @see Student
	 */
	public Student odrediStudentaZaRektorovuNagradu();
}
