package com.blog.service;

import com.blog.dao.Exchange_listDao;
import com.blog.entity.Exchange_list;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class Exchange_listService {

    @Autowired
    private Exchange_listDao dao;

    public Exchange_list getExchange_listById(Long id){
        return this.dao.finExchange_listById(id);
    }

    public List<Exchange_list> getExchange_list(String exchangeName){ return this.dao.findExchangelist(exchangeName); }


    @Transactional
    public void addExchange_list(Exchange_list exchange_list){
        this.dao.insertExchange_list(exchange_list);
    }
}
