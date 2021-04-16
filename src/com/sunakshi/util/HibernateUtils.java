package com.sunakshi.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("resourses/Hibernate.cfg.xml");
            sf = cfg.buildSessionFactory();
            Session sess = null;
        } catch (ExceptionInInitializerError ex) // use when exception occur in static or intialiser block
        {
            System.out.println("Error in hibernate util");
            throw new ExceptionInInitializerError("Exception in SessionFactory creation");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }
    public static void closeSessionFactory()
    {
        if(sf!=null)
            sf.close();
    }

}
