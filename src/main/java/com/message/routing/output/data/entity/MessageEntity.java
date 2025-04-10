package com.message.routing.output.data.entity;

import jakarta.persistence.*;

@Entity(name = "MESSAGE")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String payload;

    public MessageEntity setId(final Long id) {
        this.id = id;
        return this;
    }

    public MessageEntity setPayload(final String payload) {
        this.payload = payload;
        return this;
    }
    
    public String getPayload() {
        return payload;
    }
}
