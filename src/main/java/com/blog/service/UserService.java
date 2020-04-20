package com.blog.service;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService
 */
@Service
public class UserService{
    @Autowired
    private UserDao dao;

    /**
     * 查询所有用户
     * @param name
     * @return
     */
    public List<User>getUsers(String name){
        if(name==null)
            name="";
        return this.dao.findUsers(name);
    }
    /**
     * 根据id查找
     * @param id
     * @return
     */
    public User getUserById(Long id){
        return this.dao.findUserById(id);
    }

    /**
     * 增加用户
     * @param user
     */
    @Transactional
    public void addUser(User user){
        this.dao.insert(user);
    }

    /**
     * 修改用户
     * @param user
     */
    @Transactional
    public void editUser(User user){
        this.dao.update(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @Transactional
    public void deleteUser(Long id){
        this.dao.delete(id);
    }

    @Transactional
    public User loginUser(String username,String password){
        return this.dao.findByUsernameAndPwd(username,password);
    }
}
