package com.yigitkaya.services;

import java.sql.Blob;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yigitkaya.dto.DtoCategory;
import com.yigitkaya.entities.Category;
import com.yigitkaya.entities.FoodItem;

public interface IMenuService {

	//MENU
	public List<Category> GetMenu();
	
	//Category CRUD
	public Category SaveCategory(Category category);
	
	public Category GetCategoryById(Integer id);
	
	public void DeleteCategory(Integer id);
	
	public List<DtoCategory> GetAllCategoryIdsAndNames();
	
	public Category UpdateCategoryName(Integer id, String newName);
	
	//FoodItem CRUD
	public FoodItem GetFoodItemById(Integer id);
	
	public Category AddFoodItem(FoodItem foodItem,Integer categoryId);
	
	public void DeleteFoodItem(Integer id);
	
	public List<FoodItem> SearchFoodItemsByName(String name);

	public List<FoodItem> GetFoodItemsByPriceRange(double minPrice, double maxPrice);
	
	public FoodItem UpdateFoodItem(Integer id, FoodItem item);
	
	public void UpdateFoodImage(Integer id, Blob image);
	
	

	
	
	
	
	
	
	
	
	
	
	
}
