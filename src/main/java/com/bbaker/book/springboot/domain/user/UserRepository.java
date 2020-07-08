package com.bbaker.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
* Optional
*  - 존재할 수도 있지만 안할 수도 있는 객체 (null이 될 수도 있는 객체)
*/
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByEmail(String email);
}
