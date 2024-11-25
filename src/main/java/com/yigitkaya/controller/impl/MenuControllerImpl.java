package com.yigitkaya.controller.impl;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yigitkaya.controller.IMenuController;
import com.yigitkaya.dto.DtoCategory;
import com.yigitkaya.entities.Category;
import com.yigitkaya.entities.FoodItem;
import com.yigitkaya.services.IMenuService;

@RestController
@RequestMapping("rest/api/menu")
public class MenuControllerImpl implements IMenuController{

	@Autowired
	private IMenuService menuService;
	
	@Override
	@PostMapping(path="/category/save")
	public Category saveCategory(@RequestBody Category category) {
	
		return menuService.SaveCategory(category);
	}

	@Override
	@PostMapping(path="/category/addFoodItem/{categoryId}")
	public String AddFoodItem(@PathVariable Integer categoryId,
	        @RequestParam("name") String name,
	        @RequestParam("description") String description,
	        @RequestParam("price") BigDecimal price,
	        @RequestParam("imageFile") MultipartFile imageFile) {
		
		Blob imageBlob = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
				imageBlob = new SerialBlob(imageFile.getBytes());
			} catch (SerialException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        }

        
        FoodItem item = new FoodItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setImage(imageBlob);
		menuService.AddFoodItem(item, categoryId);
		 return "redirect:/panel";
	}

	@Override
	@GetMapping(path="/getMenu")
	public List<Category> GetMenu() {
		
		return menuService.GetMenu();
	}

	@Override
	@GetMapping(path="/getCategories")
	public List<DtoCategory> GetAllCategoryIdsAndNames() {
		return menuService.GetAllCategoryIdsAndNames();
	}

	@Override
	@GetMapping(path="/category/{id}")
	public Category GetCategoryById(@PathVariable(name="id") Integer id) {
		
		return menuService.GetCategoryById(id);
	}

	@Override
	@DeleteMapping(path="/category/delete/{id}")
	public void DeleteCategory(@PathVariable(name="id") Integer id) {
		menuService.DeleteCategory(id);
		
	}

	@Override
	@GetMapping(path="/foodItem/{id}")
	public FoodItem GetFoodItem(@PathVariable(name="id") Integer id) {
		return menuService.GetFoodItemById(id);
	}

	@Override
	@DeleteMapping(path="/foodItem/delete/{id}")
	public void DeleteFoodItem(@PathVariable(name="id") Integer id) {
		menuService.DeleteFoodItem(id);
		
	}
	@Override
	@GetMapping("/search")
    public List<FoodItem> searchFoodItemsByName(@RequestParam String name) {
        return menuService.SearchFoodItemsByName(name);
    }
	
	@Override
	@GetMapping("/price-range")
    public List<FoodItem> getFoodItemsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return menuService.GetFoodItemsByPriceRange(minPrice, maxPrice);
    }

	@Override
	@PutMapping("/category/updateName/{id}")
	public Category UpdateCategoryName(@PathVariable(name="id") Integer id,@RequestParam String name) {
		
		return menuService.UpdateCategoryName(id, name);
	}
	@Override
	@PutMapping("/foodItem/updateImage/{id}")
	public void UpdateFoodImage(@PathVariable Integer id,
	        @RequestParam("imageFile") MultipartFile imageFile){
		
		Blob imageBlob = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
				imageBlob = new SerialBlob(imageFile.getBytes());
			} catch (SerialException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        }
        menuService.UpdateFoodImage(id, imageBlob);
        
	}
	
	@Override
	@PutMapping("/foodItem/update/{id}")
	public FoodItem UpdateFoodItem(@PathVariable Integer id,
			@RequestParam("name") String name,
	        @RequestParam("description") String description,
	        @RequestParam("price") BigDecimal price,
	        @RequestParam("imageFile") MultipartFile imageFile)  {
		
		Blob imageBlob = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
				imageBlob = new SerialBlob(imageFile.getBytes());
			} catch (SerialException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        }
        FoodItem item = new FoodItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setImage(imageBlob);
		return menuService.UpdateFoodItem(id, item);
	}


	

}
