package com.message.routing.domain.model;

public record Partner(Long id, String alias, String type, Direction direction,
                      String application, FlowType flowType, String description) {
}
