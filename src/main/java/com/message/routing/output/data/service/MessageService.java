package com.message.routing.output.data.service;

import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.input.rest.mapper.MessageMapper;
import com.message.routing.output.data.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageService(final MessageRepository messageRepository, final MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public List<BackOfficeMessage> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(messageMapper::toMessage)
                .toList();
    }

    @Transactional
    public void save(final BackOfficeMessage backOfficeMessage) {
        messageRepository.save(messageMapper.toMessageEntity(backOfficeMessage));
    }
}
