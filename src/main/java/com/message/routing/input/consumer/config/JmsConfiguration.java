package com.message.routing.input.consumer.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConfiguration {
    private final String concurrencyMin;
    private final String concurrencyMax;

    public JmsConfiguration(@Value("${app.concurrency.size.min}") final String concurrencyMin,
                            @Value("${app.concurrency.size.max}") final String concurrencyMax) {
        this.concurrencyMin = concurrencyMin;
        this.concurrencyMax = concurrencyMax;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency(String.format("%s-%s", concurrencyMin, concurrencyMax));
        return factory;
    }

}
