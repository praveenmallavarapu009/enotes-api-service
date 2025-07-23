package com.becoder.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.CategoryResponse;
import com.becoder.entity.Category;
import com.becoder.repository.CategoryRepository;
import com.becoder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		
		
		
		Category category=new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());
		
		BeanUtils.copyProperties(categoryDto, category);
		
	
		category.setCreatedBy(1);
		
		Category savedCategory=categoryRepository.save(category);
		
		
		
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		return true;
	}


	@Override
	public List<CategoryDto> getAllCategory() {
	    return categoryRepository.findAll()
	            .stream()
	            .map(category -> {
	                CategoryDto dto = new CategoryDto();
	                BeanUtils.copyProperties(category, dto);
	                return dto;
	            })
	            .collect(Collectors.toList());
	}


	@Override
	public List<CategoryResponse> getActiveCategory() {
	    List<Category> categories = categoryRepository.findByIsActiveTrue();

	    List<CategoryResponse> responses = categories.stream()
	        .map(category -> {
	            CategoryResponse response = new CategoryResponse();
	            BeanUtils.copyProperties(category, response);
	            return response;
	        })
	        .collect(Collectors.toList());

	    return responses;
	}

	
}


	



	

	

