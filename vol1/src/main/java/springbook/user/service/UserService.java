package springbook.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import springbook.user.domain.User;

@Transactional
public interface UserService {

	void add(User user);
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, timeout=30)
	User get(String id);
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, timeout=30)
	List<User> getAll();
	
	void deleteAll();
	
	void update(User user);
	
	void upgradeLevels();

}
