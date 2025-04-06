package com.message.routing.input.rest.controller;

import com.message.routing.domain.model.Partner;
import com.message.routing.input.rest.mapper.PartnerMapper;
import com.message.routing.output.data.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("partner")
public class PartnerController {
    private final PartnerService partnerService;

    @Autowired
    public PartnerController(final PartnerService partnerService,
                             final PartnerMapper partnerMapper) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public List<Partner> getAll() {
        return partnerService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final Partner partner) {
        partnerService.save(partner);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        partnerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}