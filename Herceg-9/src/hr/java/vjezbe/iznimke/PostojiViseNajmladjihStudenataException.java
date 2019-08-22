package hr.java.vjezbe.iznimke;

/**
 * Predstavlaj izuzetak koji se dogodi kada postoji više najmaðih studenata
 * @author Mario
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException {
	private static final long serialVersionUID = -296381963817458610L;
	public PostojiViseNajmladjihStudenataException() {
		super("Dogodila se pogreška u radu programa!");
	}
	public PostojiViseNajmladjihStudenataException(String message) {
		super(message);
	}
	public PostojiViseNajmladjihStudenataException(String message, Throwable cause) {
		super(message, cause);
	}
	public PostojiViseNajmladjihStudenataException(Throwable cause) {
		super(cause);
	}
}
