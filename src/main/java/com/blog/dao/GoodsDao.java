package com.blog.dao;

import com.blog.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * GoodsDao
 */
@Mapper
@Component
public interface GoodsDao {

    /**
     * 查询所有商品
     * @param category
     * @return
     */
    @Select("select name,stock from goods where category = #{category}")
    public List<Goods> getGoods(@Param(value = "category")String category);

    @Select("select category from goods group by category")
    public List<Goods> getCategories();

    @Select("select id from goods where name =#{name}")
    public String getGoodsNameById(String name);

    @Select("select * from goods where name like '%${name}%'")
    public List<Goods>findGoodslist(@Param(value = "name")String name);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Select("select * from goods where id =#{id}")
    public Goods findGoodsById(Long id);

    /**
     * 增加商品
     * @param goods
     */
    @Insert("insert into goods(category,name,stock,price,PIC) values(#{category},#{name},#{stock},#{price},#{PIC})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void insert(Goods goods);

    /**
     * 修改商品
     * @param goods
     */
    @Update("update goods set category=#{category},name=#{name},stock=#{stock},price=#{price},PIC=#{PIC} where id=#{id}")
    public void update(Goods goods);

    /**
     * 删除商品
     * @param id
     */
    @Delete("delete from goods where id=#{id}")
    public void delete(Long id);
}
