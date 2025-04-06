package com.message.routing.output.data.service;

import com.message.routing.domain.model.Partner;
import com.message.routing.input.rest.mapper.PartnerMapper;
import com.message.routing.output.data.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;


    @Autowired
    public PartnerService(final PartnerRepository partnerRepository, final PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    public List<Partner> findAll() {
        return partnerRepository.findAll()
                .stream()
                .map(partnerMapper::toPartner)
                .toList();
    }


    public void save(final Partner partner) {
        partnerRepository.save(partnerMapper.toPartnerEntity(partner));
    }

    public void delete(final Long id) {
        partnerRepository.deleteById(id);
    }
}
