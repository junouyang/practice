/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2016
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

import static junit.framework.TestCase.assertFalse;

import org.hibernate.jpa.internal.EntityManagerImpl;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import javax.persistence.EntityManager;

public class HQLCrossJoinTest {
    private PersistenceManagerStandalone pm;
    //    private static EntityManagerProvider provider = new EntityManagerProvider(pm);
    private EntityManager entityManager;
    private SessionFactoryImpl sessionFactory;

    @Before
    public void setUp() {
        pm = StandAlonePersistenceManagerFactory.get();
        pm.startTransaction();
        Persistence
        //        provider = new EntityManagerProvider(pm);
        entityManager = pm.getEntityManager();
        EntityManagerImpl entityManagerImpl = (EntityManagerImpl)entityManager;
        HibernateEntityManagerFactory hibernateEntityManagerFactory = (HibernateEntityManagerFactory)entityManagerImpl.getFactory();
        sessionFactory = (SessionFactoryImpl) hibernateEntityManagerFactory.getSessionFactory();
    }

    @After
    public void tearDown() {
        pm.endTransaction();
    }

    @Test
    public void testNoCrossJoin() {
        assertNoCrossJoin("hql");
    }

    private void assertNoCrossJoin(String hql) {
        String sql = convertHqlToSql(hql);
        assertFalse("\"" + sql + "\n\"contains cross join.", sql.contains(" cross join "));
    }

    private String convertHqlToSql(String hql) {
        final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
        final QueryTranslator translator = translatorFactory.createQueryTranslator(hql, hql,
                        Collections.EMPTY_MAP, sessionFactory, null);
        translator.compile(Collections.EMPTY_MAP, false);
        return translator.getSQLString();
    }
}
