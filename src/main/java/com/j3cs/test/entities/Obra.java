/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "Obra.findAll", query = "SELECT o FROM Obra o"),
    @NamedQuery(name = "Obra.findByIdObra", query = "SELECT o FROM Obra o WHERE o.idObra = :idObra"),
    @NamedQuery(name = "Obra.findByTitulo", query = "SELECT o FROM Obra o WHERE o.titulo = :titulo"),
    @NamedQuery(name = "Obra.findByEditora", query = "SELECT o FROM Obra o WHERE o.editora = :editora"),
    @NamedQuery(name = "Obra.findByFechaPublicacion", query = "SELECT o FROM Obra o WHERE o.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Obra.findByIdAutor", query = "SELECT o FROM Obra o WHERE o.idAutor = :idAutor")})
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OBRA")
    private BigDecimal idObra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String titulo;
    @Size(max = 100)
    private String editora;
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_AUTOR")
    private String idAutor;

    public Obra() {
    }

    public Obra(BigDecimal idObra) {
        this.idObra = idObra;
    }

    public Obra(BigDecimal idObra, String titulo, String idAutor) {
        this.idObra = idObra;
        this.titulo = titulo;
        this.idAutor = idAutor;
    }

    public BigDecimal getIdObra() {
        return idObra;
    }

    public void setIdObra(BigDecimal idObra) {
        this.idObra = idObra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObra != null ? idObra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obra)) {
            return false;
        }
        Obra other = (Obra) object;
        if ((this.idObra == null && other.idObra != null) || (this.idObra != null && !this.idObra.equals(other.idObra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.j3cs.test.entities.Obra[ idObra=" + idObra + " ]";
    }
    
}
