package com.techflow.ws.sys.entity;

public enum SysHistoryTypeEnum {
    SISTEMA(1),
    USUARIO(2),
    SUPORTE(3),
    RELATORIO(4),
    LOGIN(5);


    private final int id;
    SysHistoryTypeEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
}
