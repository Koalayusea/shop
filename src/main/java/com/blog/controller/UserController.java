package com.blog.controller;

import com.blog.entity.Goods;
import com.blog.entity.User;
import com.blog.service.Exchange_listService;
import com.blog.service.GoodsService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 */
@Controller
@RequestMapping("/admin1")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private Exchange_listService exchange_listService;

    /**
     * 查询所有用户
     *
     * @param userName
     * @param model
     * @return
     */
    @GetMapping
    public String userlist(String userName, String goodsName, String exchangeName, Model model) {
        model.addAttribute("userList", this.userService.getUsers(userName));
        model.addAttribute("userTitle", "用户管理");
        model.addAttribute("goodsList", this.goodsService.getGoodslist(goodsName));
        model.addAttribute("goodsTitle", "上架商品");
        model.addAttribute("exchange_List", this.exchange_listService.getExchange_list(exchangeName));
        model.addAttribute("exchange_Title", "交易记录");

        model.addAttribute("user", new User());
        model.addAttribute("useraddTitle", "创建用户");
        model.addAttribute("goods", new Goods());
        model.addAttribute("goodsaddTitle", "增加商品");



        return "admin1/admin";
    }

//    @GetMapping
//    public String goodslist(String category,Model model){
//        model.addAttribute("goodsList",this.goodsService.getGoods(category));
//        model.addAttribute("goodsTitle","上架商品");
//        return "/admin1/index";
//    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public String view(Long id, Model model) {
        User user = this.userService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return "users/view";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return "redirect:/admin1";
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteGoods/{id}")
    public String deleteGoods(@PathVariable("id") Long id) {
        this.goodsService.deleteGoods(id);
        return "redirect:/admin1";
    }

    /**
     * 创建用户
     *
     * @param
     * @return
     */
    @PostMapping("/addUser")
    public String addUser(User user) {
        this.userService.addUser(user);
        return "redirect:/admin1";
    }

    /**
     * 增加商品
     * @param goods
     * @return
     */
    @PostMapping("/addGoods")
    public String addGoods(Goods goods){
        this.goodsService.addGoods(goods);
        return "redirect:/admin1";
    }
    /**
     * 修改用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return "admin1/edit_user";
    }

    @PostMapping("/modifyUser")
    public String modifyUser(User user) {
        this.userService.editUser(user);
        return "redirect:/admin1";
    }

    /**
     * 修改商品
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editGoods/{id}")
    public String editGoods(@PathVariable Long id, Model model) {
        Goods goods = this.goodsService.getGoodsById(id);
        model.addAttribute("goods", goods);
        model.addAttribute("title", "修改商品");
        return "admin1/edit_goods";
    }

    @PostMapping("modifyGoods")
    public String modifyGoods(Goods goods) {
        this.goodsService.editGoods(goods);
        return "redirect:/admin1";
    }
}
