package springbook.user.sqlservice.exception;

public class SqlUpdateFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SqlUpdateFailureException() {
		super();
	}

	public SqlUpdateFailureException(String message) {
		super(message);
	}

	public SqlUpdateFailureException(Throwable cause) {
		super(cause);
	}

	public SqlUpdateFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
