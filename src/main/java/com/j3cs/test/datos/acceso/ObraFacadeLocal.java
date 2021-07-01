/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.datos.acceso;

import com.j3cs.test.entities.Obra;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jcastro
 */
public class ObraFacadeLocal extends PruebaFacade<Obra> implements ObraLocal{
     @PersistenceContext(unitName = "prod")
    private EntityManager em;
    
    public ObraFacadeLocal(){
        super(Obra.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
