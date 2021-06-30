/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j3cs.test.controller;

import com.j3cs.test.controller.exceptions.NonexistentEntityException;
import com.j3cs.test.controller.exceptions.PreexistingEntityException;
import com.j3cs.test.entities.Ejemplar;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
public class EjemplarJpaController implements Serializable {

    public EjemplarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejemplar ejemplar) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ejemplar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjemplar(ejemplar.getIdEjemplar()) != null) {
                throw new PreexistingEntityException("Ejemplar " + ejemplar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejemplar ejemplar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ejemplar = em.merge(ejemplar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ejemplar.getIdEjemplar();
                if (findEjemplar(id) == null) {
                    throw new NonexistentEntityException("The ejemplar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejemplar ejemplar;
            try {
                ejemplar = em.getReference(Ejemplar.class, id);
                ejemplar.getIdEjemplar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejemplar with id " + id + " no longer exists.", enfe);
            }
            em.remove(ejemplar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejemplar> findEjemplarEntities() {
        return findEjemplarEntities(true, -1, -1);
    }

    public List<Ejemplar> findEjemplarEntities(int maxResults, int firstResult) {
        return findEjemplarEntities(false, maxResults, firstResult);
    }

    private List<Ejemplar> findEjemplarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejemplar.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ejemplar findEjemplar(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejemplar.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjemplarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejemplar> rt = cq.from(Ejemplar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
