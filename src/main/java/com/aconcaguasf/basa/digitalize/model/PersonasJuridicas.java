package com.aconcaguasf.basa.digitalize.model;

import org.hibernate.annotations.BatchSize;
import javax.persistence.*;

/**
 * Created by acorrea on 03/07/2017.
 */
@BatchSize(size = 50)
@Entity
@Table(name = "personas_juridicas")
public class PersonasJuridicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String razonSocial;

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}




