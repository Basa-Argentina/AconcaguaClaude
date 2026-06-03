package com.aconcaguasf.basa.digitalize.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * Created by acorrea on 03/07/2017.
 */
@BatchSize(size = 50)
@Entity
@Table(name = "clientesEmp")
public class ClienteEmp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String razonSocial_id;

    @Column(nullable = false)
    private Long empresa_id;

    @Column(nullable = false)
    private Long codigo;

    @Column(nullable = false)
    private String empresaAdministra;

    @Transient
    private String sector;





    @ManyToOne
    @JoinColumn(name="razonSocial_id", insertable=false, updatable=false)
    private PersonasJuridicas personasJuridicas;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteEmp_id", fetch = FetchType.LAZY)
    private List<ClienteEmpleados> clienteEmpleados;

    @ManyToOne
    @JoinColumn(name="empresa_id", insertable=false, updatable=false)
    private Empresas empresas;

    public List<ClienteEmpleados> getClienteEmpleados() {
        return clienteEmpleados;
    }

    public void setClienteEmpleados(List<ClienteEmpleados> clienteEmpleados) {
        this.clienteEmpleados = clienteEmpleados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(Long empresa_id) {
        this.empresa_id = empresa_id;
    }

    public Empresas getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Empresas empresas) {
        this.empresas = empresas;
    }

    public PersonasJuridicas getPersonasJuridicas() {
        return personasJuridicas;
    }

    public void setPersonasJuridicas(PersonasJuridicas personasJuridicas) {
        this.personasJuridicas = personasJuridicas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial_id() {
        return razonSocial_id;
    }

    public void setRazonSocial_id(String razonSocial_id) {
        this.razonSocial_id = razonSocial_id;
    }


    public String getEmpresaAdministra() {
        return empresaAdministra;
    }

    public void setEmpresaAdministra(String empresaAdministra) {
        this.empresaAdministra = empresaAdministra;
    }
}




