package hr.java.vjezbe.entitet;

/**
 * Predstavlja su�elje Diplomski koja nasslje�uje Visokoskolska
 * @author Mario
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Odre�uje najboljeg studenta godine
	 * @return objekt klase Student
	 * @see Student
	 */
	public Student odrediStudentaZaRektorovuNagradu();
}
