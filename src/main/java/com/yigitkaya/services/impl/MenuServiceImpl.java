package com.yigitkaya.services.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yigitkaya.dto.DtoCategory;
import com.yigitkaya.entities.Category;
import com.yigitkaya.entities.FoodItem;
import com.yigitkaya.repository.CategoryRepository;
import com.yigitkaya.repository.FoodItemRepository;
import com.yigitkaya.services.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	@Override
	public List<Category> GetMenu() {
		List<Category> menu = categoryRepository.findAll();
		
		return menu;
	}
	
	@Override
	public Category SaveCategory(Category category) {
		Category savedCategory = new Category();
		savedCategory = categoryRepository.save(category);
		
		return savedCategory;
	}

	@Override
	public Category GetCategoryById(Integer id) {
		
		Category category = new Category();
		Optional<Category> optional = categoryRepository.findById(id);
		if(optional.isPresent()) {
			category = optional.get();
		
		}
		return category;
	}


	@Override
	public void DeleteCategory(Integer id) {
		Category category = new Category();
		
		Optional<Category> optional = categoryRepository.findById(id);
		if(optional.isPresent()) {
			category = optional.get();
			
			categoryRepository.delete(category);
		}
		else {
			//throw
		}
		
		
	}

	@Override
	public FoodItem GetFoodItemById(Integer id) {
		
		Optional<FoodItem> optional =foodItemRepository.findById(id);
		if(optional.isPresent()){
			return optional.get();
		}
		return null;
	}

	@Override
	public Category AddFoodItem(FoodItem foodItem, Integer categoryId) {
		
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent())
        {
            Category category = optionalCategory.get();
            category.addFoodItem(foodItem);  
            
            return categoryRepository.save(category);
        } 
        else 
        {
            throw new RuntimeException("Category not found with id: " + categoryId);
        }
		
	}

	@Override
	public void DeleteFoodItem(Integer id) {
		FoodItem foodItem = new FoodItem();
		
		Optional<FoodItem> optional = foodItemRepository.findById(id);
		if(optional.isPresent()) {
			foodItem = optional.get();
			
			foodItemRepository.delete(foodItem);
		}
		else {
			//throw
		}
		
	}

	@Override
	public List<DtoCategory> GetAllCategoryIdsAndNames() {
		
		List<DtoCategory> dtoList = new ArrayList<>();
		List<Category> categories =categoryRepository.findAll();
		
		for(Category category : categories) {
			DtoCategory dtoCategory = new DtoCategory();
			BeanUtils.copyProperties(category, dtoCategory);
			dtoList.add(dtoCategory);
		}

		return dtoList;
	
	}

	@Override
	public List<FoodItem> SearchFoodItemsByName(String name) {

		 return foodItemRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<FoodItem> GetFoodItemsByPriceRange(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return foodItemRepository.findByPriceBetween(minPrice, maxPrice);
	}

	@Override
	public Category UpdateCategoryName(Integer id, String newName) {
		Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        category.setName(newName);
        return categoryRepository.save(category);
	}
	public MenuServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FoodItem UpdateFoodItem(Integer id, FoodItem item) {
		FoodItem newFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("İçerik bulunamadı"));

		
		newFoodItem.setName(item.getName());
		newFoodItem.setDescription(item.getDescription());
		newFoodItem.setPrice(item.getPrice());
		if(item.getImage() != null) {
			newFoodItem.setImage(item.getImage());
		}
		
		return foodItemRepository.save(newFoodItem);
	}

	@Override
	public void UpdateFoodImage(Integer id, Blob image) {
		FoodItem newFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("İçerik bulunamadı"));

		newFoodItem.setImage(image);
		foodItemRepository.save(newFoodItem);
		
	}

	

	
	

}
