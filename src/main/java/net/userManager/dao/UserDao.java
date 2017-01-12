package net.userManager.dao;

import net.userManager.Model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 24.11.2016.
 */
public interface UserDao {
    public void add(User user);

    public void update(User user);

    public void delete(int id);

    public User getUserById(int id);

    public List<User> listUsers(Integer first, String name2);

    public Map<Integer, Integer> listPages(/*Integer first, */String name2);

}
