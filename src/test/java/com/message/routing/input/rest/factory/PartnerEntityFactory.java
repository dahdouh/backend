package com.message.routing.input.rest.factory;

import com.message.routing.domain.model.Direction;
import com.message.routing.domain.model.FlowType;
import com.message.routing.output.data.entity.PartnerEntity;

public class PartnerEntityFactory {

    public static final String ALIAS = "alias";
    public static final String TYPE = "type";
    public static final String APPLICATION = "application";
    public static final String DESCRIPTION = "description";
    public static final Direction DIRECTION = Direction.INBOUND;
    public static final FlowType FLOW_TYPE = FlowType.NOTIFICATION;

    private String alias = ALIAS;
    private String type = TYPE;
    private Direction direction = DIRECTION;
    private String application = APPLICATION;
    private FlowType flowType = FLOW_TYPE;
    private String description = DESCRIPTION;

    public PartnerEntity build() {
        return new PartnerEntity()
                .alias(alias)
                .type(type)
                .direction(direction)
                .application(application)
                .flowType(flowType)
                .description(description);
    }

    public PartnerEntityFactory setAlias(final String alias) {
        this.alias = alias;
        return this;
    }

    public PartnerEntityFactory setType(final String type) {
        this.type = type;
        return this;
    }

    public PartnerEntityFactory setDirection(final Direction direction) {
        this.direction = direction;
        return this;
    }

    public PartnerEntityFactory setApplication(final String application) {
        this.application = application;
        return this;
    }

    public PartnerEntityFactory setFlowType(final FlowType flowType) {
        this.flowType = flowType;
        return this;
    }

    public PartnerEntityFactory setDescription(final String description) {
        this.description = description;
        return this;
    }
}
