package com.message.routing.domain.model;

import java.io.Serializable;

public record Message(String payload) implements Serializable {
}
