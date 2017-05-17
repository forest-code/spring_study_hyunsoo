package springbook.user.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.AppContext;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = AppContext.class)
public class UserServiceTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserService testUserService;	
	
	@Autowired
	UserDao userDao;

	@Autowired
	DataSource dataSource;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Autowired
	MailSender mailSender;

	List<User> users;

	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;

	@Before
	public void setUp() {
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "bumjin@gmail.com"),
				new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@gmail.com"),
				new User("erwins", "신승환", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1, "erwins@gmail.com"),
				new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "madnite1@gmail.com"),
				new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "green@gmail.com"));
	}

	static class MockMailSender implements MailSender {

		private List<String> requests = new ArrayList<String>();

		public List<String> getRequests() {
			return this.requests;
		}

		@Override
		public void send(SimpleMailMessage simpleMessage) throws MailException {
			requests.add(simpleMessage.getTo()[0]);
		}

		@Override
		public void send(SimpleMailMessage[] simpleMessages) throws MailException {

		}
	}

	@Test
	public void mockUpgradeLevels() throws Exception {
		// given
		UserServiceImpl userServiceImpl = new UserServiceImpl();

		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);

		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);

		// when
		userServiceImpl.upgradeLevels();

		// then
		verify(mockUserDao).update(users.get(1));
		assertThat(users.get(1).getLevel(), is(Level.SILVER));
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevel(), is(Level.GOLD));

		ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		assertThat(mailMessages.get(0).getTo()[0], is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0], is(users.get(3).getEmail()));
	}

	@Test
	@DirtiesContext
	public void upgradeLevels() throws SQLException {
		userDao.deleteAll();

		for (User user : users) {
			userDao.add(user);
		}

		MockMailSender mockMailSender = new MockMailSender();

		userService.upgradeLevels();

		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
	}

	@Test
	public void add() {
		userDao.deleteAll();

		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);

		userService.add(userWithLevel);
		userService.add(userWithoutLevel);

		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	}
	
	@Test
	public void advisorAutoProxyCreatorTest() throws Exception{
		assertThat(testUserService, is(java.lang.reflect.Proxy.class));
	}

	@Test
	public void upgradeAllOrNothing() throws Exception {
		userDao.deleteAll();

		for (User user : users) {
			System.out.println(user.getId() + ", " + user.getName() + ", " + user.getLevel());
			userDao.add(user);
		}

		try {
			this.testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		} catch (TestUserServiceException e) {

		}
		checkLevelUpgraded(users.get(1), false);
	}
	
	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());

		if (upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		} else {
			System.out.println(userUpdate.getId() + ", " + userUpdate.getName() + ", " + userUpdate.getLevel());
			System.out.println(user.getId() + ", " + user.getName() + ", " + user.getLevel());
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}

	private void checkLevel(User user, Level expectedLevel) {
		User userUpdate = userDao.get(user.getId());
		assertThat(userUpdate.getLevel(), is(expectedLevel));
	}
	
	// Read-Only 트랜잭션 속성이 설정된 메소드에 쓰기작업을 할 때 실패하는지 테스트
	@Test(expected=TransientDataAccessResourceException.class)
	public void readOnlyTransactionAttribute() {
		testUserService.getAll();
	}
	
	public static class TestUserService extends UserServiceImpl {
		private String id = "madnite1";		// user.get(3)

		protected void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) {
				throw new TestUserServiceException();
			}
			super.upgradeLevel(user);
		}
		
		@Override
		public List<User> getAll() {
			for (User user : super.getAll()) {
				super.update(user);
			}
			return null;
		}
	}
	
	@Test
	public void transactionSync() {
//		userDao.deleteAll();
//		assertThat(userDao.getCount(), is(0));
		
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
//		txDefinition.setReadOnly(true);
		TransactionStatus txStatus = transactionManager.getTransaction(txDefinition);
//		userService.deleteAll();
		
		try {
			userService.deleteAll();
			userService.add(users.get(0));
			userService.add(users.get(1));
		} finally {
			transactionManager.rollback(txStatus);
		}
	}
	
	@Test
	@Transactional
	public void txAnnotationTest() {
		userService.deleteAll();
	}
	
	@Test(expected = TransientDataAccessResourceException.class)
	@Transactional(readOnly=true)
	public void txReadOnlyTest() {
		userService.deleteAll();
	}
	
	@Test
	@Transactional
//	@Rollback(false)
	public void rollbackAnnotationTest() {
		userService.deleteAll();
	}
}

class TestUserServiceException extends RuntimeException {

}