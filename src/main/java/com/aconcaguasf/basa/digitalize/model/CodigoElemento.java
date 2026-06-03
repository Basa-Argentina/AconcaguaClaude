package com.aconcaguasf.basa.digitalize.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * Created by acorrea on 03/07/2017.
 */
@Entity
@Table(name = "elementos")
public class CodigoElemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @JsonIgnore
    @OneToMany(mappedBy = "elemento_id", fetch = FetchType.LAZY)
    private List<RemitosDetalle> remitosDetalleList;



    public List<RemitosDetalle> getRemitosDetalleList() {
        return remitosDetalleList;
    }

    public void setRemitosDetalleList(List<RemitosDetalle> remitosDetalleList) {
        this.remitosDetalleList = remitosDetalleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
