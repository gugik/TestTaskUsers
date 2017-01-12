package net.userManager.service;

import net.userManager.Model.User;
import net.userManager.dao.UserDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 24.11.2016.
 */

public class UserServiceImp implements UserService{

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void add(User user) {
        this.userDao.add(user);
    }

    @Transactional
    public void update(User user) {
        this.userDao.update(user);
    }
    @Transactional
    public void delete(int id) {
        this.userDao.delete(id);
    }
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }
    @Transactional
    public List<User> listUsers(Integer first, String name2) {
        return this.userDao.listUsers(first, name2);
    }
    @Transactional
    public Map<Integer, Integer> listPages(String name2) {
        return this.userDao.listPages(name2);    }

}
