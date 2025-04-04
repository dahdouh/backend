package com.message.routing.output.data.adapter;

import com.message.routing.output.data.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MessageDataService {

    private final MessageRepository repository;

    @Autowired
    public MessageDataService(final MessageRepository repository) {
        this.repository = repository;
    }

    public List<MessageEntity> findAll() {
        return repository.findAll();
    }

    @Transactional
    public MessageEntity save(final MessageEntity messageEntity) {
        return repository.save(messageEntity);
    }
}
