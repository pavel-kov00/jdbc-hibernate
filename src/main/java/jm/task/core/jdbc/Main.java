package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.*;

public class Main {

    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
//        UserService users = new UserServiceImpl();
//        users.createUsersTable();
//
//        users.saveUser("Bodryi","Bobrov", (byte) 17);
//        users.saveUser("Taras","Bulba",(byte) 48);
//        users.saveUser("Simon","Bolivar",(byte) 59);
//        users.saveUser("Satoshi","Nakomato",(byte) 94);
//        System.out.println(users.getAllUsers().toString());
//        users.cleanUsersTable();
//        System.out.println(users.getAllUsers());
//        users.dropUsersTable();

//        ************* hibernate ********************

        UserDao users = new UserDaoHibernateImpl();
        users.createUsersTable();

        users.saveUser("Hodryi","Bobrov", (byte) 17);
        users.saveUser("Haras","Bulba",(byte) 48);
        users.saveUser("Himon","Bolivar",(byte) 59);
        users.saveUser("Hatoshi","Nakomato",(byte) 94);
        System.out.println(users.getAllUsers().toString());
        users.cleanUsersTable();
        System.out.println(users.getAllUsers());
        users.dropUsersTable();

//        User user = new User("Pavel", "Kovylin", (byte) 24);
//        Session session = Util.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        session.save(user);
//        user.setAge((byte)126);
//        session.getTransaction().commit();

    }
}
