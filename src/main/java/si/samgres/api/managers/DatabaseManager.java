package si.samgres.api.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.HashMap;
import java.util.List;

public class DatabaseManager {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() {
        initialize(); //ensure objects

        //ensure session
        if (session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void initialize() {
        if (configuration == null) { //ensure singleton
            configuration = new Configuration().configure("hibernate.cfg.xml");

            //add entities
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Post.class);
        }
        if (sessionFactory == null) { //ensure singleton
            sessionFactory = configuration.buildSessionFactory();
        }
    }

    public static boolean add(Object newObject) {
        initialize(); //ensure objects

        //try adding
        try {
            //create transaction
            Transaction tx = getSession().beginTransaction();
            getSession().save(newObject);
            tx.commit();

            //ok
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; //fail
        }
    }

    public static <T> List getAll(Class<T> type) {
        initialize(); //ensure objects

        //try getting
        List objects = null;
        try {
            //get objects
            Query query = getSession().createQuery("from " + type.getSimpleName());
            objects = query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    public static boolean update(Object object) {
        initialize(); //ensure objects

        //try adding
        try {
            //create transaction
            Transaction tx = getSession().beginTransaction();
            getSession().update(object);
            tx.commit();

            //ok
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; //fail
        }
    }

    public static boolean persist(Object object) {
        initialize(); //ensure objects

        //try adding
        try {
            //create transaction
            Transaction tx = getSession().beginTransaction();
            getSession().persist(object);
            tx.commit();

            //ok
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; //fail
        }
    }

    public static boolean remove(Object object) {
        initialize(); //ensure objects

        //try adding
        try {
            //create transaction
            Transaction tx = getSession().beginTransaction();
            getSession().remove(object);
            tx.commit();

            //ok
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; //fail
        }
    }
}
