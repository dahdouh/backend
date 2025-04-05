package com.message.routing.output.data.service;

import com.message.routing.output.data.entity.PartnerEntity;
import com.message.routing.output.data.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartnerService {
    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerService(final PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public List<PartnerEntity> findAll() {
        return partnerRepository.findAll();
    }


    public void save(final PartnerEntity partnerEntity) {
        partnerRepository.save(partnerEntity);
    }

    public void delete(final Long id) {
        partnerRepository.deleteById(id);
    }
}
