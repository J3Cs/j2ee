/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.datos.acceso;

import com.j3cs.test.entities.Autor;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jcastro
 */
@Stateless
public class AutorFacadeLocal extends PruebaFacade<Autor> implements AutorLocal{
    @PersistenceContext(unitName = "prod")
    private EntityManager em;
    
    public AutorFacadeLocal(){
        super(Autor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Autor> obraPorAutor(BigDecimal idObra){
        return em.createNamedQuery("Autor.ObrasPorModelo").setParameter("idObra", idObra).getResultList();
    }
    
}
