package com.message.routing.output.data.service;

import com.message.routing.domain.model.Partner;
import com.message.routing.input.rest.factory.PartnerEntityFactory;
import com.message.routing.input.rest.factory.PartnerFactory;
import com.message.routing.input.rest.mapper.PartnerMapper;
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
    public static final PartnerEntity PARTNER_ENTITY = new PartnerEntityFactory().build();
    public static final Partner PARTNER = new PartnerFactory().build();
    @Mock
    private PartnerRepository partnerRepository;
    @Mock
    private PartnerMapper partnerMapper;
    @InjectMocks
    private PartnerService partnerService;

    @Test
    void shouldGetAllPartnerEntities() {
        //given
        Mockito.when(partnerRepository.findAll()).thenReturn(List.of(PARTNER_ENTITY));
        Mockito.when(partnerMapper.toPartner(PARTNER_ENTITY)).thenReturn(PARTNER);

        // when
        final List<Partner> results = partnerService.findAll();

        //then
        Mockito.verify(partnerRepository, only()).findAll();
        Assertions.assertThat(results).isNotEmpty();
        Assertions.assertThat(results.getFirst()).usingRecursiveComparison().isEqualTo(PARTNER);
    }

    @Test
    void shouldSavePartnerEntity() {
        //given
        Mockito.when(partnerMapper.toPartnerEntity(PARTNER)).thenReturn(PARTNER_ENTITY);

        // when
        partnerService.save(PARTNER);

        //then
        Mockito.verify(partnerMapper, only()).toPartnerEntity(PARTNER);
        Mockito.verify(partnerRepository, only()).save(PARTNER_ENTITY);
    }

    @Test
    void shouldDeletePartnerEntity() {
        //given && when
        partnerService.delete(1L);

        //then
        Mockito.verify(partnerRepository, only()).deleteById(1L);
    }
}