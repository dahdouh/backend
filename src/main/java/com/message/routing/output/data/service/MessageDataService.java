package com.message.routing.output.data.service;

import com.message.routing.output.data.entity.MessageEntity;
import com.message.routing.output.data.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MessageDataService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageDataService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    @Transactional
    public void save(final MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
    }
}
