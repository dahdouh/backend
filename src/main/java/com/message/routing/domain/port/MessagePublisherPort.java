package com.message.routing.domain.port;

import com.message.routing.domain.model.Message;

public interface MessagePublisherPort {

    void publish(final Message message);
}
