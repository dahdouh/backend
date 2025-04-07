package com.message.routing.application.loader;

import com.message.routing.domain.model.*;
import com.message.routing.output.data.service.PartnerService;
import com.message.routing.output.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class DataLoader implements ApplicationRunner {
    private final Resource messageFile;
    private final UserService userService;
    private final PartnerService partnerService;
    private final JmsTemplate jmsTemplate;
    private final String queueName;


    @Autowired
    public DataLoader(final UserService userService,
                      final PartnerService partnerService,
                      final JmsTemplate jmsTemplate,
                      @Value("${app.queue.name}") final String queueName,
                      @Value("classpath:data/message.xml") final Resource messageFile) {
        this.userService = userService;
        this.partnerService = partnerService;
        this.messageFile = messageFile;
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    @Override
    public void run(final ApplicationArguments args) throws IOException {
        userService.save(new User("admin", "admin"));
        final BackOfficeMessage backOfficeMessage = new BackOfficeMessage(messageFile.getContentAsString(UTF_8));
        jmsTemplate.convertAndSend(queueName, backOfficeMessage);
        partnerService.save(new Partner(1L, "alias1", "type1", Direction.INBOUND, "app1", FlowType.NOTIFICATION, "description1"));
    }
}
