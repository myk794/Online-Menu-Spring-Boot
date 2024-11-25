package com.yigitkaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yigitkaya.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
