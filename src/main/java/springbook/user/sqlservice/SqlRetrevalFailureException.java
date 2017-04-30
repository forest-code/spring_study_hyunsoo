package springbook.user.sqlservice;

public class SqlRetrevalFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SqlRetrevalFailureException(String message) {
		super(message);
	}
	
	public SqlRetrevalFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
