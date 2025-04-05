package com.message.routing.output.data.service;

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
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;

    @Test
    void shouldGetAllMessageEntities() {
        //given && when
        Mockito.when(messageRepository.findAll()).thenReturn(List.of(MESSAGE_ENTITY));
        final List<MessageEntity> results = messageService.findAll();

        //then
        Mockito.verify(messageRepository, only()).findAll();
        Assertions.assertThat(results).isNotEmpty();
        Assertions.assertThat(results.getFirst()).usingRecursiveComparison().isEqualTo(MESSAGE_ENTITY);
    }

    @Test
    void shouldSaveMessageEntity() {
        //given && when
        messageService.save(MESSAGE_ENTITY);

        //then
        Mockito.verify(messageRepository, only()).save(MESSAGE_ENTITY);
    }


}