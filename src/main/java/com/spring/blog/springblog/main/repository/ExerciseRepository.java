package com.spring.blog.springblog.main.repository;

import com.spring.blog.springblog.main.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Board, Integer> {

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}