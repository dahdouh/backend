package com.message.routing.input.rest;

import com.message.routing.domain.model.Message;
import com.message.routing.domain.port.MessagePublisherPort;
import com.message.routing.input.mapper.MessageMapper;
import com.message.routing.output.data.adapter.MessageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("message")
public class MessageController {
    private final MessagePublisherPort messagePublisherPort;
    private final MessageDataService messageDataService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageController(final MessagePublisherPort messagePublisherPort, final MessageDataService messageDataService, final MessageMapper messageMapper) {
        this.messagePublisherPort = messagePublisherPort;
        this.messageDataService = messageDataService;
        this.messageMapper = messageMapper;
    }

    @GetMapping
    public List<Message> getAll() {
        return messageDataService.findAll()
                .stream()
                .map(messageMapper::toMessage)
                .toList();
    }

    @PostMapping("publish")
    public String publish(@RequestBody final Message message) {
        messagePublisherPort.publish(message);
        return String.format("Message successfully published %s", message);
    }
}