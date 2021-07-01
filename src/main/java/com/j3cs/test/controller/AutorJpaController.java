/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.controller;

import com.j3cs.test.controller.exceptions.PreexistingEntityException;
import com.j3cs.test.datos.acceso.AutorLocal;
import com.j3cs.test.datos.acceso.GenericInterface;
import com.j3cs.test.datos.acceso.ObraLocal;
import com.j3cs.test.entities.Autor;
import com.j3cs.test.entities.Obra;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jcastro
 */
@Named(value = "AutorBean")
@ViewScoped
public class AutorJpaController extends GenericBean<Autor>implements Serializable {
    
    @PersistenceContext(unitName = "prod")
    EntityManager em;
    
    @EJB
    AutorLocal autor;
    @EJB
    ObraLocal obra;
    @Inject
    Autor nuevo;
    @Inject
    Obra obraModel;
    
    public AutorJpaController() {
        nuevo = new Autor();
    }
    
    @PostConstruct
    public void init(){
        getAll();
    }
    
    public void limpiar(){
        nuevo.setNombre("");
        nuevo.setNacionalidad("");
        nuevo.setSexo("");
        nuevo.setObraList(null);
    }
    
    
    public void deleteAutor(Autor item){
        nuevo = item;
        delete();
    }
    
    public void rowSelected(Autor item){
        nuevo = item;
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
