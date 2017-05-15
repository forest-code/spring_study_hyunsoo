package springbook.user.dao;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import org.junit.After;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import springbook.user.sqlservice.register.EmbeddedDbSqlRegistry;
import springbook.user.sqlservice.register.UpdatableSqlRegistry;

public class EmbeddedDbSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
	
	EmbeddedDatabase db;

	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		db = new EmbeddedDatabaseBuilder()
				.setType(HSQL)
				.addScript("classpath:/springbook/user/sqlservice/sql/sqlRegistrySchema.sql")
				.build();
		
		EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();
		embeddedDbSqlRegistry.setDataSource(db);
		
		return embeddedDbSqlRegistry;
	}
	
	@After
	public void tearDown() {
		db.shutdown();
	}

}
