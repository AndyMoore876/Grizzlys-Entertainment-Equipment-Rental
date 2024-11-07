package server.factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.User;

public class HibernateSessionFactoryBuilder {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {

        if(sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass
                    (User.class).buildSessionFactory();
        }

        return sessionFactory;
    }

    public static void closeSessionFactory(){
        if(sessionFactory != null ) {
//			session.flush();
            sessionFactory.close();
        }
    }
}
