package com.message.routing.output.data.entity;

import com.message.routing.domain.model.Direction;
import com.message.routing.domain.model.FlowType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "PARTNER")
public class PartnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String alias;
    private String type;
    private Direction direction;
    private String application;
    private FlowType flowType;
    private String description;

    public PartnerEntity alias(final String alias) {
        this.alias = alias;
        return this;
    }

    public PartnerEntity type(final String type) {
        this.type = type;
        return this;
    }

    public PartnerEntity direction(final Direction direction) {
        this.direction = direction;
        return this;
    }

    public PartnerEntity application(final String application) {
        this.application = application;
        return this;
    }

    public PartnerEntity flowType(final FlowType flowType) {
        this.flowType = flowType;
        return this;
    }

    public PartnerEntity description(final String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getApplication() {
        return application;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public String getDescription() {
        return description;
    }
}
