package com.spring.blog.springblog.main.repository;

import com.spring.blog.springblog.main.entity.FoodList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodList, Integer> {
}