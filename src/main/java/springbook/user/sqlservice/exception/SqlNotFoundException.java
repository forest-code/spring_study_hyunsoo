package springbook.user.sqlservice.exception;

public class SqlNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SqlNotFoundException() {
		super();
	}
	
	public SqlNotFoundException(String message) {
		super(message);
	}
	
	public SqlNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SqlNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
