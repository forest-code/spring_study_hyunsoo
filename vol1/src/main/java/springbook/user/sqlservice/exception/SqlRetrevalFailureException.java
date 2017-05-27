package springbook.user.sqlservice.exception;

public class SqlRetrevalFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SqlRetrevalFailureException() {
		super();
	}
	
	public SqlRetrevalFailureException(String message) {
		super(message);
	}
	
	public SqlRetrevalFailureException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SqlRetrevalFailureException(Throwable cause) {
		super(cause);
	}

}
