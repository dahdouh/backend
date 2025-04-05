package com.message.routing.output.data.service;

import com.message.routing.input.rest.factory.PartnerEntityFactory;
import com.message.routing.output.data.entity.PartnerEntity;
import com.message.routing.output.data.repository.PartnerRepository;
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
class PartnerServiceTest {
    @Mock
    private PartnerRepository partnerRepository;
    @InjectMocks
    private PartnerService partnerService;

    @Test
    void shouldGetAllPartnerEntities() {
        //given
        final PartnerEntity partnerEntity = new PartnerEntityFactory().build();
        Mockito.when(partnerRepository.findAll()).thenReturn(List.of(partnerEntity));
        // when
        final List<PartnerEntity> results = partnerService.findAll();

        //then
        Mockito.verify(partnerRepository, only()).findAll();
        Assertions.assertThat(results).isNotEmpty();
        Assertions.assertThat(results.getFirst()).usingRecursiveComparison().isEqualTo(partnerEntity);
    }

    @Test
    void shouldSavePartnerEntity() {
        //given
        final PartnerEntity partnerEntity = new PartnerEntityFactory().build();

        // when
        partnerService.save(partnerEntity);

        //then
        Mockito.verify(partnerRepository, only()).save(partnerEntity);
    }

    @Test
    void shouldDeletePartnerEntity() {
        //given && when
        partnerService.delete(1L);

        //then
        Mockito.verify(partnerRepository, only()).deleteById(1L);
    }
}