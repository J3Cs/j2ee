/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.datos.acceso;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author jcastro
 * @param <T>
 */
public abstract class PruebaFacade<T> implements Serializable {
    
   protected abstract EntityManager getEntityManager();
   private Class<T> EntityClass;
   
   public PruebaFacade(Class<T> EntityClass){
       this.EntityClass = EntityClass;
   }
   
   public void create(T entity){
       EntityManager em = getEntityManager();
       try {
           em.persist(entity);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }
   
    public void delete(T entity) {
        EntityManager em = getEntityManager();
        em.remove(em.merge(entity));
    }

    public void update(T entity) {
        EntityManager em = getEntityManager();
        em.merge(entity);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(EntityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
