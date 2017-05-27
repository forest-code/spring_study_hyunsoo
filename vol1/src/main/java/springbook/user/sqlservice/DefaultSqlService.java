package springbook.user.sqlservice;

import springbook.user.sqlservice.reader.JaxbXmlSqlReader;
import springbook.user.sqlservice.register.HashMapSqlRegistry;

public class DefaultSqlService extends BaseSqlService {
	
	public DefaultSqlService() {
		setSqlReader(new JaxbXmlSqlReader());
		setSqlRegistry(new HashMapSqlRegistry());
	}

}
