/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.controller;

import com.j3cs.test.controller.exceptions.NonexistentEntityException;
import com.j3cs.test.controller.exceptions.PreexistingEntityException;
import com.j3cs.test.datos.acceso.AutorLocal;
import com.j3cs.test.datos.acceso.GenericInterface;
import com.j3cs.test.entities.Autor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jcastro
 */
@Named(value = "AutorBean")
@ViewScoped
public class AutorJpaController extends GenericBean<Autor>implements Serializable {
    
    @EJB
    AutorLocal autor;
    
    Autor nuevo = new Autor();
    
    public AutorJpaController() {
        
    }
    
    @PostConstruct
    public void init(){
        getAll();
    }

    public AutorLocal getAutor() {
        return autor;
    }

    public void setAutor(AutorLocal autor) {
        this.autor = autor;
    }

    public Autor getNuevo() {
        return nuevo;
    }

    public void setNuevo(Autor nuevo) {
        this.nuevo = nuevo;
    }
    
    
    
    @Override
    public GenericInterface<Autor> getfacadelocal() {
        return autor;
    }

    @Override
    public Autor getEntity() {
        return this.nuevo;
    }
    
}
