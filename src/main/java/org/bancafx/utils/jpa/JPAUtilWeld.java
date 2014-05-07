package org.bancafx.utils.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

/**
 * Created by Douglas on 06/05/2014.
 */

@ApplicationScoped
public class JPAUtilWeld implements Serializable {

    private static final long serialVersionUID = -7412603409431578181L;

    private static final String PUName = "BancaFXPU";

    private EntityManagerFactory factory;

    public JPAUtilWeld(){
        factory = Persistence.createEntityManagerFactory(PUName);
    }

    @Produces
    public EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager em){
        em.close();
    }

}