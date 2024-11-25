package com.yigitkaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import com.yigitkaya.entities.Category;
import com.yigitkaya.entities.FoodItem;
import com.yigitkaya.services.IMenuService;

@Controller
public class MenuViewController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/menu")
    public String showMenu(Model model) {
        List<Category> menu = menuService.GetMenu();
        menu.sort(Comparator.comparing(Category::getId));
        for(Category category : menu) {
        	List<FoodItem> list = category.getFoodItems();
        	list.sort(Comparator.comparing(FoodItem::getId));
        	category.setFoodItems(list);
        }
        
        model.addAttribute("menu", menu);
        return "index";
    }
    
    @Transactional
    @GetMapping("/foodItem/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        FoodItem item = menuService.GetFoodItemById(id);
        if (item != null && item.getImage() != null) {
            try {
                byte[] imageBytes = item.getImage().getBytes(1, (int) item.getImage().length());
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }
}

