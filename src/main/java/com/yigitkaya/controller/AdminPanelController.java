package com.yigitkaya.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yigitkaya.entities.Category;
import com.yigitkaya.entities.FoodItem;
import com.yigitkaya.services.IMenuService;

@Controller
public class AdminPanelController {

	 @Autowired
	 private IMenuService menuService;
	 
	 public Boolean loggedIn = false;
	 
	 @GetMapping("/panel")
	 public String adminPanel(Model model) {
		 List<Category> menu = menuService.GetMenu();
	        menu.sort(Comparator.comparing(Category::getId));
	        for(Category category : menu) {
	        	List<FoodItem> list = category.getFoodItems();
	        	list.sort(Comparator.comparing(FoodItem::getId));
	        	category.setFoodItems(list);
	        }
	        model.addAttribute("categories", menu);
	     return "adminPanel";
	     }
}
