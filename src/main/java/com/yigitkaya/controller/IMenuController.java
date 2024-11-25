package com.yigitkaya.controller;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.yigitkaya.dto.DtoCategory;
import com.yigitkaya.entities.Category;
import com.yigitkaya.entities.FoodItem;

public interface IMenuController {

	public Category saveCategory(Category category);
	
	public List<FoodItem> searchFoodItemsByName(String name);
	
	public List<Category> GetMenu();
	
	public List<DtoCategory> GetAllCategoryIdsAndNames();
	
	public Category GetCategoryById(Integer id);
	
	public void DeleteCategory(Integer id);
	
	public FoodItem GetFoodItem(Integer id);
	
	public void DeleteFoodItem(Integer id);
	
	public List<FoodItem> getFoodItemsByPriceRange(double minPrice,double maxPrice);

	public Category UpdateCategoryName(Integer id, String newName);
	
	public FoodItem UpdateFoodItem(Integer id, String name, String description, BigDecimal price,
			MultipartFile imageFile);

	public String AddFoodItem(Integer categoryId, String name, String description, BigDecimal price,
			MultipartFile imageFile);
	
	public void UpdateFoodImage(Integer id, MultipartFile imageFile);
	
	
}
