package com.message.routing.input.rest.mapper;

import com.message.routing.domain.model.User;
import com.message.routing.output.data.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toUserEntity(final User user) {
        return new UserEntity()
                .username(user.username())
                .password(user.password());
    }
}
