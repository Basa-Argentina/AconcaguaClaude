package com.aconcaguasf.basa.digitalize.model;

import javax.persistence.*;
import java.sql.Date;


/**
 * Created by acorrea on 03/07/2017.
 */
@Entity
@Table(name = "elementos")
public class ElementosLite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String UremitoCons;



    @Column(nullable = false)
    private String remitoBaja;

    @Column
    private String remitoVacias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUremitoCons(String uremitoCons) {
        UremitoCons = uremitoCons;
    }

    public void setRemitoBaja(String remitoBaja) {
        this.remitoBaja = remitoBaja;
    }

    public void setRemitoVacias(String remitoVacias) {
        this.remitoVacias = remitoVacias;
    }

}
