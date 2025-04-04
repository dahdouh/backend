package com.message.routing.input.mapper;

import com.message.routing.domain.model.Message;
import com.message.routing.output.data.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message toMessage(final MessageEntity messageEntity) {
        return new Message(messageEntity.getPayload());
    }

    public Message toMessage(final Message messageEntity) {
        return new Message(messageEntity.getPayload());
    }
}
