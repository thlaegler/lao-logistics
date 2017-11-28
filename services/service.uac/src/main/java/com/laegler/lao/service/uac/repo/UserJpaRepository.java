package com.laegler.lao.service.uac.repo;

import com.laegler.lao.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

	User findFirstByUsername(final String username);

	User findFirstByEmail(final String email);

}
