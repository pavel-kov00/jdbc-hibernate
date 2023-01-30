package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = null;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("create table if not exists users " +
                    "(   id       bigint(20) not null auto_increment ," +
                    "    name     varchar(40) null,"   +
                    "    lastname varchar(40) null,"   +
                    "    age      tinyint     null,"   +
                    "    primary key(id)           "   +
                    ") ;").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {

        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("drop table if exists users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(new User(name,lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {

        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(User.class,id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery("from User",
                    User.class);
            list = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}