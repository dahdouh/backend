package com.message.routing.output.data.service;

import com.message.routing.output.data.entity.MessageEntity;
import com.message.routing.output.data.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(final MessageRepository messageRepository) {
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
