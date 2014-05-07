package org.bancafx.utils.jpa;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Douglas on 01/05/2014.
 */
public class JPAUtil {

    private static final String PUName = "BancaFXPU";

    private static ThreadLocal<EntityManager> manager = new ThreadLocal<EntityManager>();

    private static EntityManagerFactory factory;



    public static boolean isEntityManagerOpen(){
        return JPAUtil.manager.get() != null && JPAUtil.manager.get().isOpen();
    }

    public static EntityManager getEntityManager() {
        if (JPAUtil.factory == null) {
            JPAUtil.factory = Persistence.createEntityManagerFactory(PUName);
        }
        EntityManager em = JPAUtil.manager.get();
        if (em == null || !em.isOpen()) {
            em = JPAUtil.factory.createEntityManager();
            JPAUtil.manager.set(em);
        }
        return em;
    }

    public static void evictCache(EntityManager em, String region){
        ((Session)em.getDelegate()).getSessionFactory().getCache().evictQueryRegion(region);
    }

    public static void closeEntityManager() {
        EntityManager em = JPAUtil.manager.get();
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            if (tx.isActive()) {
                tx.commit();
            }
            em.close();
            JPAUtil.manager.set(null);
        }
    }

    public static void closeEntityManagerFactory(){
        closeEntityManager();
        JPAUtil.factory.close();
    }

}
