package sem4.src.model;

/**
 * Thrown when create, read or delete of an item fails.
 */
public class ItemNotFoundException extends Exception {
	/**
	 * Create a new instance thrown because of the specified reason.
	 *
	 * @param reason Why the exception was thrown.
	 */
	public ItemNotFoundException(String reason) {
		super(reason);
	}

	/**
	 * Create a new instance thrown because of the specified reason and exception.
	 *
	 * @param reason    Why the exception was thrown.
	 * @param rootCause The exception that caused this exception to be thrown.
	 */
	public ItemNotFoundException(String reason, Throwable rootCause) {
		super(reason, rootCause);
	}
}