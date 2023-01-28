package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3306/studytestdb";
    private static String USERNAME = "root";
    private static String PASSWORD = "1234";
    private static Connection connection;

//    private static SessionFactory sessionFactory;

    public Util (){

    }

    public static Connection getConnection()  throws SQLException  {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return connection;
    }

    public static SessionFactory getSessionFactory () {
//        Properties prop= new Properties();
//        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/studytestdb");
//        prop.setProperty("hibernate.connection.username", "root");
//        prop.setProperty("hibernate.connection.password", "1234");
//        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//        if (sessionFactory == null) {
        SessionFactory sessionFactory = null;
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/studytestdb?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }

        return sessionFactory;
    }

}
