package com.spring.boot.actuator.repository;

import com.spring.boot.actuator.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hyunsoo0813 on 2017. 8. 17..
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
}
