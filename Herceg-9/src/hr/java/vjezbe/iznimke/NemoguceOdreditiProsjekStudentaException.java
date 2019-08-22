package hr.java.vjezbe.iznimke;

/**
 * Predstavlja izuzetak koji se dogodi kada je nemoguuæe odrediti prosjek studenta zbog ocjene nedovoljan
 * @author Mario
 *
 */
public class NemoguceOdreditiProsjekStudentaException extends Exception {
	private static final long serialVersionUID = 267880090245711736L;
	public NemoguceOdreditiProsjekStudentaException() {
		super("Dogodila se pogreška u radu programa!");
	}
	public NemoguceOdreditiProsjekStudentaException(String message) {
		super(message);
	}
	public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause) {
		super(message, cause);
	}
	public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
		super(cause);
	}
}
