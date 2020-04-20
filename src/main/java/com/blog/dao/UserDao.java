package com.blog.dao;

import com.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserDap
 */
@Mapper
@Component
public interface UserDao {

    /**
     * 查询所有用户
     * @param name
     * @return
     */
    @Select("select * from user where name like '%${name}%'")
    public List<User>findUsers(@Param(value = "name")String name);
    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Select("select * from user where id =#{id}")
    public User findUserById(Long id);

    /**
     * 增加用户
     * @param user
     */
    @Insert("insert into user(name,username,password,mobile) values(#{name},#{username},#{password},#{mobile})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void insert(User user);

    /**
     * 修改用户
     * @param user
     */
    @Update("update user set name=#{name},password=#{password},mobile=#{mobile} where id=#{id}")
    public void update(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from User where id=#{id}")
    public void delete(Long id);

    /**
     *使用@Param来传递参数,传入参数为username和password
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user as u where u.username=#{username} and u.password=#{password}")
    public User findByUsernameAndPwd(@Param("username") String username,@Param("password")String password);
}
