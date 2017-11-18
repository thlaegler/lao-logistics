package com.laegler.lao.service.uac.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

	User findByUsername(final String username);

	User findByEmail(final String email);

}
