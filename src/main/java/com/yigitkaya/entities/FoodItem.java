package com.yigitkaya.entities;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "foodItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name= "name", nullable = false)
	private String name;
	
	@Column(name= "description", nullable = true)
	private String description;
	
	@Column(name= "price", nullable = true)
	private BigDecimal price;
	
	@Column(name= "image", nullable = true)
	@JsonIgnore
	private Blob image;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
	@JsonBackReference
	private Category category;
	
}

