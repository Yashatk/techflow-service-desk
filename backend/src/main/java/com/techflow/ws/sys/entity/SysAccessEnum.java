package com.techflow.ws.sys.entity;

public enum SysAccessEnum {
    CADASTRAR_CLIENTE("Cadastrar Cliente"),
    CHAMADO_STATUS_ALTERAR("Alterar Status do Chamado"),
    CHAMADO_PRIORIDADE_DEFINIR("Definir Prioridade do Chamado"),
    RELATORIO("Gerar Relatório"),
    CADASTRAR_ANALISTA("Cadastrar Analista");

    private String descricao;

    SysAccessEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
