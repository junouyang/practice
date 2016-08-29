import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.jpa.internal.EntityManagerImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.test.Employee;
import org.test.MyClass;
import org.test.Phone;

import java.util.Collections;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HQLCrossJoinTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private SessionFactoryImpl sessionFactory;
    private EntityTransaction transaction;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeService");
        entityManager = entityManagerFactory.createEntityManager();
        EntityManagerImpl entityManagerImpl = (EntityManagerImpl)entityManager;
        HibernateEntityManagerFactory hibernateEntityManagerFactory = entityManagerImpl.getFactory();
        sessionFactory = (SessionFactoryImpl) hibernateEntityManagerFactory.getSessionFactory();
        transaction = entityManager.getTransaction();
        transaction.begin();
        createEmployAndPhones();
    }

    @After
    public void tearDown() {
//        transaction.commit();
    }

    private void createEmployAndPhones( ) {
        Employee employee1 = createEmployee(1L, "Jun", "Ouyang", "IT");
        Employee employee2 = createEmployee(2L, "Huirong", "Zhang", "Consultant");
        Phone phone = createPhone(employee1, 1L, "6502005389");
        Phone phone1 = createPhone(employee1, 2L, "6502005390");
        Phone phone2 = createPhone(employee2, 3L, "6502005391");
        Phone phone3 = createPhone(employee2, 4L, "6502005392");
        createClass("Compute Science");
        createClass("Stock investment");
        createClass("Education");

        transaction.commit();
    }

    private Phone createPhone(Employee employee1, long id, String number) {
        Phone existingPhone = entityManager.find(Phone.class, id);
        if( existingPhone != null ) return existingPhone;
        Phone phone = new Phone(id, number);
        phone.setOwner(employee1);
        entityManager.persist(phone);
        return phone;
    }

    private MyClass createClass(String name) {
        MyClass myClass = new MyClass(name);
        entityManager.persist(myClass);
        return myClass;
    }

    private Employee createEmployee(long id, String firstName, String lastName, String dept) {
        Employee existingEmployee = entityManager.find(Employee.class, id);
        if( existingEmployee != null ) return existingEmployee;
        Employee employee = new Employee(id, firstName, lastName, dept);
        entityManager.persist(employee);
        return employee;
    }

    @Test
    public void testNoCrossJoin() {
        String sql = convertHqlToSql("select p.id, p.owner.id from Phone p where p.id = 1");
        System.out.println("======== " + sql);
        assertFalse("\"" + sql + "\n\"contains cross join.", sql.contains(" cross join "));

        String sql2 = convertHqlToSql("select p.owner.id from Phone p, MyClass c");
        System.out.println("======== " + sql2);
        assertThat(sql2).contains(" cross join ");
    }

    private String convertHqlToSql(String hql) {
        final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
        final QueryTranslator translator = translatorFactory.createQueryTranslator(hql, hql,
                        Collections.EMPTY_MAP, sessionFactory, null);
        translator.compile(Collections  .EMPTY_MAP, false);
        return translator.getSQLString();
    }
}
