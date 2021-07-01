/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.controller;

import com.j3cs.test.datos.acceso.GenericInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jcastro
 */
public abstract class GenericBean<T> implements Serializable {
    
    T e = getEntity();
    List<T> lista = new ArrayList<>();
    
    public GenericBean(){
    }

    public T getE() {
        return e;
    }

    public void setE(T e) {
        this.e = e;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
    
    public void create(){
        if (getfacadelocal() != null) {
            try {
                getfacadelocal().create(getEntity());
                setE(getEntity());
            } catch (Exception err) {
                    System.out.println(err.getMessage());
            }
        }
    }
    
    public void update(){
        if (getfacadelocal() != null) {
            try {
                getfacadelocal().update(getEntity());
                setE(getEntity());
            } catch (Exception err) {
                    System.out.println(err.getMessage());
            }
        }
    }
    
    public void delete(){
        if (getfacadelocal() != null) {
            try {
                getfacadelocal().delete(getEntity());
                setE(getEntity());
            } catch (Exception err) {
                    System.out.println(err.getMessage());
            }
        }
    }
    
    public void getAll(){
        if(getfacadelocal().findAll() != null){
            this.lista = getfacadelocal().findAll();
        }
        else{
            this.lista = Collections.EMPTY_LIST;
        }
    }
    
    public abstract GenericInterface<T> getfacadelocal();

    public abstract T getEntity();


}
