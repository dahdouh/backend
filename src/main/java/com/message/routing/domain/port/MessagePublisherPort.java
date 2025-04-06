package com.message.routing.domain.port;

import com.message.routing.domain.model.BackOfficeMessage;

public interface MessagePublisherPort {
    void publish(final BackOfficeMessage backOfficeMessage);
}
