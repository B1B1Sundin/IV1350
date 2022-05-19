package sem4.src.intergration;

/**
 * Thrown when the database can't be reached
 */
public class DataBaseException extends Exception {

	/**
	 * Create a new instance thrown because of the specified reason.
	 *
	 * @param reason Why the exception was thrown.
	 */
	public DataBaseException(String reason) {

		super(reason);
	}

	/**
	 * Create a new instance thrown because of the specified reason and exception.
	 *
	 * @param reason    Why the exception was thrown.
	 * @param rootCause The exception that caused this exception to be thrown.
	 */
	public DataBaseException(String reason, Throwable rootCause) {
		super(reason, rootCause);
	}
}
