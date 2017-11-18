package com.laegler.lao.service.user.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.laegler.lao.model.entity.User;
import com.laegler.lao.service.user.repo.UserJpaRepository;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserJpaRepository userJpaRepository;

	public User addUser(final User user) {
		return userJpaRepository.save(user);
	}

	public Page<User> getAllUsers(final PageRequest pageRequest) {
		return userJpaRepository.findAll(pageRequest);
	}

	public User getUserById(final long id) {
		return userJpaRepository.findOne(id);
	}
}
