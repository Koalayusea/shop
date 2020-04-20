package com.blog.dao;

import com.blog.entity.Exchange_list;
import com.blog.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface Exchange_listDao {
    /**
     * 根据id查list
     * @param id
     * @return
     */
    @Select("select * from Exchange_list where id=#{id}")
    public Exchange_list finExchange_listById(Long id);

    /**
     * 查询所有交易记录
     * @param name
     * @return
     */
    @Select("select * from Exchange_list where name like '%${name}%'")
    public List<Exchange_list> findExchangelist(@Param(value = "name")String name);

    /**
     * 增加商品
     * @param exchange_list
     */
    @Insert("insert into Exchange_list  values(#{id},CURDATE(), CURTIME(),#{buy_count},#{name},#{address},#{email})")
    public void insertExchange_list(Exchange_list exchange_list);
}
