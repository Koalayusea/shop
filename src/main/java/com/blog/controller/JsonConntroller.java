package com.blog.controller;

import com.blog.entity.Exchange_list;
import com.blog.entity.Goods;
import com.blog.service.Exchange_listService;
import com.blog.service.GoodsService;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class JsonConntroller {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private Exchange_listService exchange_listService;

    @RequestMapping("/category")
    @ResponseBody
    public  List<Map<String, Object>> getCategoriesInfo() {
        List<Map<String, Object>> rawData = new ArrayList<>();

        List<String> categoriesName = this.goodsService.getCategories();

        //构造分类节点test
        for(int i=0;i<categoriesName.size();i++){
            Map<String, Object> categoryNode = new HashMap<>();

            //节点名称 [text]
            categoryNode.put("text",categoriesName.get(i));
            System.out.println(categoriesName.get(i));
            //节点可选性
            categoryNode.put("selectable",false);

            //构造商品信息变量Map [nodes]
            List<String> productNamesTemp=this.goodsService.getGoods(categoriesName.get(i));
            List<Object> productNode=new ArrayList<>();


             //构造子节点信息[nodes]
            for(int y=0;y<productNamesTemp.size();y++){
                Map<String,String> productNodeTemp = new HashMap<>();
                productNodeTemp.put("text",productNamesTemp.get(y));
                productNodeTemp.put("type","child");
                productNodeTemp.put("id",this.goodsService.getGoodsNameById(productNamesTemp.get(y)));
                productNode.add(productNodeTemp);
            }
            //添加商品节点map
            categoryNode.put("nodes",productNode);

            //子节点count [tag]
            List<String> tag = new ArrayList<>();
            tag.add(Integer.toString(productNamesTemp.size()));
            categoryNode.put("tags",tag);

            rawData.add(categoryNode);
        }

        return rawData;
    }

    @RequestMapping("/product/{id}")
    @ResponseBody
    public Goods getProduct(@PathVariable("id") Long id){

     //   this.goodsService.getGoodsById(id);
        return this.goodsService.getGoodsById(id);
    }

    @RequestMapping("/exchange_list/{id}")
    @ResponseBody
    public Exchange_list getExchange_list(@PathVariable("id") Long id){
        return this.exchange_listService.getExchange_listById(id);
    }


    @RequestMapping("/addExchange_list")
    @ResponseBody
    public Map<String,Object>getSubmit(@RequestBody Map<String,Object> map){

        //定义对象用于返回前端
        Map<String,Object> getHtml = new HashMap<>();
        getHtml.put("code",0);
        getHtml.put("msg","");

        List<Map<String,Object>> rawList = (List)map.get("list");

        //定义map存放用户所购买的商品清单
        Map<String,Integer> productList = new HashMap<>();
        String id ="";

        //构造用户购买的商品清单
        for(int i=0;i<rawList.size();i++) {
            int purchaseQuantity = 0;
            if (productList.containsKey(rawList.get(i).get("id"))) {
                purchaseQuantity = Integer.parseInt((String) rawList.get(i).get("purchaseQuantity")) + productList.get(rawList.get(i).get("id"));
                System.out.println("product existed");
            } else {
                purchaseQuantity = Integer.parseInt((String) rawList.get(i).get("purchaseQuantity"));
            }
            productList.put((String) rawList.get(i).get("id"), purchaseQuantity);

        }



        //将数据插入数据库foreaach
        for(String key:productList.keySet()){
            Exchange_list exchange_list = new Exchange_list();
            System.out.println(key + ": " +  productList.get(key));
            exchange_list.setId(Long.parseLong(key));
            exchange_list.setBuy_count(productList.get(key));
            exchange_list.setName((String)map.get("name"));
            exchange_list.setAddress((String)map.get("address"));
            exchange_list.setEmail((String)map.get("email"));
            this.exchange_listService.addExchange_list(exchange_list);
        }
        return getHtml;
    }


}
