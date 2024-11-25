package com.yigitkaya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yigitkaya.entities.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer>{

	public List<FoodItem> findByNameContainingIgnoreCase(String name);
	
	public List<FoodItem> findByPriceBetween(double minPrice, double maxPrice);
}
