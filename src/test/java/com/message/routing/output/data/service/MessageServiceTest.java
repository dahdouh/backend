package com.message.routing.output.data.service;

import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.input.rest.mapper.MessageMapper;
import com.message.routing.output.data.entity.MessageEntity;
import com.message.routing.output.data.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    public static final MessageEntity MESSAGE_ENTITY = new MessageEntity().setPayload("payload");
    public static final BackOfficeMessage MESSAGE = new BackOfficeMessage("payload");
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private MessageMapper messageMapper;
    @InjectMocks
    private MessageService messageService;

    @Test
    void shouldGetAllMessageEntities() {
        //given && when
        Mockito.when(messageRepository.findAll()).thenReturn(List.of(MESSAGE_ENTITY));
        Mockito.when(messageMapper.toMessage(MESSAGE_ENTITY)).thenReturn(MESSAGE);
        final List<BackOfficeMessage> results = messageService.findAll();

        //then
        Mockito.verify(messageRepository, only()).findAll();
        Mockito.verify(messageMapper, only()).toMessage(MESSAGE_ENTITY);
        Assertions.assertThat(results).isNotEmpty();
        Assertions.assertThat(results.getFirst()).usingRecursiveComparison().isEqualTo(MESSAGE_ENTITY);
    }

    @Test
    void shouldSaveMessageEntity() {
        //given && when
        Mockito.when(messageMapper.toMessageEntity(MESSAGE)).thenReturn(MESSAGE_ENTITY);
        messageService.save(MESSAGE);

        //then
        Mockito.verify(messageMapper, only()).toMessageEntity(MESSAGE);
        Mockito.verify(messageRepository, only()).save(MESSAGE_ENTITY);
    }


}