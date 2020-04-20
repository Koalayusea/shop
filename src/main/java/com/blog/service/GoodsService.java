package com.blog.service;

import com.blog.dao.GoodsDao;
import com.blog.entity.Exchange_list;
import com.blog.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * GoodsService
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao dao;

    /**
     * 获取分类下商品名字
     * @param categoryName
     * @return
     */
    public List<String>getGoods(String categoryName){
        List<String> goodName=new ArrayList<>();
        List<Goods> daoResults = this.dao.getGoods(categoryName);
        for(int i=0;i < daoResults.size();i++){
            goodName.add(daoResults.get(i).getName());
        }
        return goodName;
    }

    /**
     * 获取分类名字集合
     * @return
     */
    public List<String>getCategories(){
        List<Goods> daoResults =  this.dao.getCategories();

        List<String> categoryNames=new ArrayList<>();
        for(int i=0;i < daoResults.size();i++){
            categoryNames.add(daoResults.get(i).getCategory());
        }
        return categoryNames;
    }

    public String getGoodsNameById(String name){
        return this.dao.getGoodsNameById(name);
    }

    /**
     * 在后台显示
     * @return
     */
    public List<Goods>getGoodslist(String goodsName){
        return this.dao.findGoodslist(goodsName);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Goods getGoodsById(Long id){
        return this.dao.findGoodsById(id);
    }

    /**
     * 增加商品
     * @param goods
     */
    @Transactional
    public void addGoods(Goods goods){
        this.dao.insert(goods);
    }

    /**
     * 修改商品
     * @param goods
     */
    @Transactional
    public void editGoods(Goods goods){
        this.dao.update(goods);
    }

    /**
     * 删除商品
     * @param id
     */
    @Transactional
    public void deleteGoods(Long id){
        this.dao.delete(id);
    }

}
