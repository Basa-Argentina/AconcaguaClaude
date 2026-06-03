package com.aconcaguasf.basa.digitalize.dto;

public class ElementosOperacionDTO {
    private String codigo;
    private Long operacionId;

    // Constructor que acepta los parámetros de la consulta
    public ElementosOperacionDTO(String codigo, Long operacionId) {
        this.codigo = codigo;
        this.operacionId = operacionId;
    }



    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getOperacionId() {
        return operacionId;
    }

    public void setOperacionId(Long operacionId) {
        this.operacionId = operacionId;
    }
}