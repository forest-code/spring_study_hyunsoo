package com.spring.boot.actuator.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hyunsoo0813 on 2017. 8. 17..
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person (성=");
        builder.append(this.lastName);
        builder.append(", 이름=");
        builder.append(this.firstName);
        builder.append(")");
        return builder.toString();
    }
}
