package springbook.user.sqlservice;

import springbook.user.sqlservice.exception.SqlRetrevalFailureException;

public interface SqlService {
	
	String getSql(String key) throws SqlRetrevalFailureException;

}
