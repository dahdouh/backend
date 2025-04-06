package com.message.routing.input.rest.mapper;

import com.message.routing.domain.model.Partner;
import com.message.routing.output.data.entity.PartnerEntity;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {
    public PartnerEntity toPartnerEntity(final Partner partner) {
        return new PartnerEntity()
                .alias(partner.alias())
                .type(partner.type())
                .direction(partner.direction())
                .application(partner.application())
                .flowType(partner.flowType())
                .description(partner.description());
    }

    public Partner toPartner(final PartnerEntity partnerEntity) {
        return new Partner(partnerEntity.getId(),
                partnerEntity.getAlias(),
                partnerEntity.getType(),
                partnerEntity.getDirection(),
                partnerEntity.getApplication(),
                partnerEntity.getFlowType(),
                partnerEntity.getDescription());
    }
}
