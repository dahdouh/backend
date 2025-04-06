package com.message.routing.input.rest.controller;

import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.domain.port.MessagePublisherPort;
import com.message.routing.output.data.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("message")
public class MessageController {
    private final MessagePublisherPort messagePublisherPort;
    private final MessageService messageService;

    @Autowired
    public MessageController(final MessagePublisherPort messagePublisherPort, final MessageService messageService) {
        this.messagePublisherPort = messagePublisherPort;
        this.messageService = messageService;
    }

    @GetMapping
    public List<BackOfficeMessage> getAll() {
        return messageService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> publish(@RequestBody final BackOfficeMessage backOfficeMessage) {
        messagePublisherPort.publish(backOfficeMessage);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Message successfully published");
    }
}