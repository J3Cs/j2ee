/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jcastro
 */
@Entity
@Table(catalog = "", schema = "BIBLIOTECA")
@NamedQueries({
    @NamedQuery(name = "Ejemplar.findAll", query = "SELECT e FROM Ejemplar e"),
    @NamedQuery(name = "Ejemplar.findByIdEjemplar", query = "SELECT e FROM Ejemplar e WHERE e.idEjemplar = :idEjemplar"),
    @NamedQuery(name = "Ejemplar.findByNumEjemplar", query = "SELECT e FROM Ejemplar e WHERE e.numEjemplar = :numEjemplar"),
    @NamedQuery(name = "Ejemplar.findByEstadoConservacion", query = "SELECT e FROM Ejemplar e WHERE e.estadoConservacion = :estadoConservacion"),
    @NamedQuery(name = "Ejemplar.findByFechaAlquiler", query = "SELECT e FROM Ejemplar e WHERE e.fechaAlquiler = :fechaAlquiler"),
    @NamedQuery(name = "Ejemplar.findByFechaDevolucion", query = "SELECT e FROM Ejemplar e WHERE e.fechaDevolucion = :fechaDevolucion")})
public class Ejemplar implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EJEMPLAR")
    private BigDecimal idEjemplar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_EJEMPLAR")
    private BigInteger numEjemplar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESTADO_CONSERVACION")
    private String estadoConservacion;
    @Column(name = "FECHA_ALQUILER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlquiler;
    @Column(name = "FECHA_DEVOLUCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    @JoinTable(name = "EJEMPLAR_POR_OBRA", joinColumns = {
        @JoinColumn(name = "ID_EJEMPLAR", referencedColumnName = "ID_EJEMPLAR")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_OBRA", referencedColumnName = "ID_OBRA")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Obra> obraList;

    public Ejemplar() {
    }

    public Ejemplar(BigDecimal idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public Ejemplar(BigDecimal idEjemplar, BigInteger numEjemplar, String estadoConservacion) {
        this.idEjemplar = idEjemplar;
        this.numEjemplar = numEjemplar;
        this.estadoConservacion = estadoConservacion;
    }

    public BigDecimal getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(BigDecimal idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public BigInteger getNumEjemplar() {
        return numEjemplar;
    }

    public void setNumEjemplar(BigInteger numEjemplar) {
        this.numEjemplar = numEjemplar;
    }

    public String getEstadoConservacion() {
        return estadoConservacion;
    }

    public void setEstadoConservacion(String estadoConservacion) {
        this.estadoConservacion = estadoConservacion;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public List<Obra> getObraList() {
        return obraList;
    }

    public void setObraList(List<Obra> obraList) {
        this.obraList = obraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjemplar != null ? idEjemplar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejemplar)) {
            return false;
        }
        Ejemplar other = (Ejemplar) object;
        if ((this.idEjemplar == null && other.idEjemplar != null) || (this.idEjemplar != null && !this.idEjemplar.equals(other.idEjemplar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.j3cs.test.entities.Ejemplar[ idEjemplar=" + idEjemplar + " ]";
    }
    
}
