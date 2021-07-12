package ru.onetwo33.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private static final String DB_PU = "ru.onetwo33.entity";

    private static final EntityManagerFactorySingleton singleton = new EntityManagerFactorySingleton();

    protected EntityManagerFactory emf;

    public static EntityManagerFactorySingleton getInstance() {
        return singleton;
    }

    private EntityManagerFactorySingleton() {}

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null)
            createEntityManagerFactory();
        return emf;
    }

    private void createEntityManagerFactory() {
        this.emf = Persistence.createEntityManagerFactory(DB_PU);
    }
}
