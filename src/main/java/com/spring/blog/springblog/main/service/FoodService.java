package com.spring.blog.springblog.main.service;

import com.spring.blog.springblog.main.entity.Board;
import com.spring.blog.springblog.main.entity.FoodList;
import com.spring.blog.springblog.main.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<FoodList> findAll() {
        return foodRepository.findAll();
    }

    public FoodList findByFoodId(int id) {
        return foodRepository.findById(id).get();
    }
}