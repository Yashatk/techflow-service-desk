package com.techflow.ws.support.entity;

public enum TicketStatusEnum {
    ABERTO(1, "Aberto"),
    EM_ANDAMENTO(2, "Em Andamento"),
    CANCELADO(3, "Cancelado"),
    RESOLVIDO(4, "Resolvido");

    private final int id;
    private final String description;

    TicketStatusEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }    
}
