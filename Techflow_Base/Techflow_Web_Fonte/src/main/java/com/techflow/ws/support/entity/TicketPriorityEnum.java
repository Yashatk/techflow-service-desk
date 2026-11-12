package com.techflow.ws.support.entity;

public enum TicketPriorityEnum {
    BAIXA(4),
    MEDIA(3),
    ALTA(2),
    URGENTE(1);

    private final int id;

    TicketPriorityEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
}
