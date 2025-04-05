package com.message.routing.input.rest.factory;

import com.message.routing.domain.model.Direction;
import com.message.routing.domain.model.FlowType;
import com.message.routing.domain.model.Partner;

public class PartnerFactory {

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

    public Partner build() {
        return new Partner(1L, alias, type, direction, application, flowType, description);
    }

    public PartnerFactory setAlias(final String alias) {
        this.alias = alias;
        return this;
    }

    public PartnerFactory setType(final String type) {
        this.type = type;
        return this;
    }

    public PartnerFactory setDirection(final Direction direction) {
        this.direction = direction;
        return this;
    }

    public PartnerFactory setApplication(final String application) {
        this.application = application;
        return this;
    }

    public PartnerFactory setFlowType(final FlowType flowType) {
        this.flowType = flowType;
        return this;
    }

    public PartnerFactory setDescription(final String description) {
        this.description = description;
        return this;
    }
}
