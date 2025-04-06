package com.message.routing.output.data.service;

import com.message.routing.domain.exception.UserNotFoundException;
import com.message.routing.domain.model.User;
import com.message.routing.input.rest.mapper.UserMapper;
import com.message.routing.output.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User finByUsernameAndPassword(final User user) {
        return ofNullable(userRepository.findByUsernameAndPassword(user.username(), user.password()))
                .map(userEntity -> new User(userEntity.getUsername(), userEntity.getPassword()))
                .orElseThrow(() -> new UserNotFoundException(user.username()));
    }

    @Transactional
    public void save(final User user) {
        userRepository.save(userMapper.toUserEntity(user));
    }
}
