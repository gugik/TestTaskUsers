package net.userManager.dao;

import net.userManager.Model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDaoImp implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImp.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. User details: " + user);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if(user!=null){
            session.delete(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }

    public User getUserById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully loaded. User details: " + user);

        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers(Integer first, String name2) {
        List<User> userList;
        Session session = this.sessionFactory.getCurrentSession();

        if (name2==null) {
            userList = session.createQuery("from User").setFirstResult(first).
                    setMaxResults(5).list();
        }
        else {
            userList = session
                    .createQuery("FROM User WHERE name LIKE :search")
                    .setParameter("search", "%" + name2 + "%")
                    .setFirstResult(first)
                    .setMaxResults(5)
                    .list();

            for (User user : userList) {
                logger.info("User list: " + user);
            }
        }
        return userList;
    }

    @SuppressWarnings("unchecked")
    public Map<Integer, Integer> listPages(/*Integer first, */String name2) {
        Session session = this.sessionFactory.getCurrentSession();

        List<User> userList/* = session.createQuery("from User").list()*/;

        if (name2==null) {
            userList = session.createQuery("from User").list();
        }
        else {
            userList = session
                    .createQuery("FROM User WHERE name LIKE :search")
                    .setParameter("search", "%" + name2 + "%")
                    .list();
        }

        Map<Integer, Integer> listPages = new HashMap<Integer, Integer>();

        int page = 1;

        for (int i = 0; i < userList.size(); i=i+5) {
            listPages.put(page, i);
            page++;
        }

        return listPages;
    }

}
