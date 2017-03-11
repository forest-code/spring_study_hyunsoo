package springbook.user.service;

import springbook.user.domain.User;
import springbook.user.service.UserServiceTest.MockMailSender;

public interface UserService {

	void add(User user);
	
	void upgradeLevels();

}
