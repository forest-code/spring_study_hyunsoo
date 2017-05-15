package springbook.user.dao;

import springbook.user.sqlservice.register.ConcurrentHashMapSqlRegistry;
import springbook.user.sqlservice.register.UpdatableSqlRegistry;

public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {

	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		return new ConcurrentHashMapSqlRegistry();
	}

}
