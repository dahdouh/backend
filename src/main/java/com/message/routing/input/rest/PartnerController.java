package com.message.routing.input.rest;

import com.message.routing.domain.model.Partner;
import com.message.routing.input.mapper.PartnerMapper;
import com.message.routing.output.data.service.PartnerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("partner")
public class PartnerController {
    private final PartnerDataService partnerDataService;
    private final PartnerMapper partnerMapper;

    @Autowired
    public PartnerController(final PartnerDataService partnerDataService,
                             final PartnerMapper partnerMapper) {
        this.partnerDataService = partnerDataService;
        this.partnerMapper = partnerMapper;
    }

    @GetMapping
    public List<Partner> getAll() {
        return partnerDataService.findAll()
                .stream()
                .map(partnerMapper::toPartner)
                .toList();
    }

    @PostMapping
    public ResponseEntity.BodyBuilder create(@RequestBody final Partner partner) {
        partnerDataService.save(partnerMapper.toPartnerEntity(partner));
        return ResponseEntity.status(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable final Long id) {
        partnerDataService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}