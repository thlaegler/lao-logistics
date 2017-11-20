package com.laegler.lao.service.uac.domain;

import com.laegler.lao.error.NotFoundException;
import com.laegler.lao.model.entity.User;
import com.laegler.lao.service.uac.repo.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Transactional
	public User addUser(final User user) {
		LOG.trace("addUser({})", user);

		return userJpaRepository.save(user);
	}

	@Transactional
	public User updateUser(final User user) {
		LOG.trace("updateUser({})", user);

		Optional.ofNullable(userJpaRepository.findOne(user.getUserId()))
				.orElseThrow(() -> new NotFoundException(String.format("User with ID %s cannot be found", user.getUserId())));

		return userJpaRepository.save(user);
	}

	@Transactional
	public void deleteUser(final long userId) {
		LOG.trace("deleteUser({})", userId);

		Optional.ofNullable(userJpaRepository.findOne(userId))
				.orElseThrow(() -> new NotFoundException(String.format("User with ID %s cannot be found", userId)));

		userJpaRepository.delete(userId);
	}

	public User getUserByUsername(final String username) {
		LOG.trace("getUserByUsername({})", username);

		return Optional.ofNullable(userJpaRepository.findFirstByUsername(username))
				.orElseThrow(() -> new NotFoundException(String.format("User with username %s cannot be found", username)));
	}

	public User getUserByEmail(final String email) {
		LOG.trace("getUserByUserId({})", email);
	
		return Optional.ofNullable(userJpaRepository.findFirstByEmail(email))
				.orElseThrow(() -> new NotFoundException(String.format("User with email %s cannot be found", email)));
	}

	public User getUserByUserId(final long userId) {
		LOG.trace("getUserByUserId({})", userId);
	
		return Optional.ofNullable(userJpaRepository.findOne(userId))
				.orElseThrow(() -> new NotFoundException(String.format("User with ID %s cannot be found", userId)));
	}

	public Page<User> getAllUsers(final PageRequest pageRequest) {
		LOG.trace("getAllUsers({})", pageRequest);

		return userJpaRepository.findAll(pageRequest);
	}
}
