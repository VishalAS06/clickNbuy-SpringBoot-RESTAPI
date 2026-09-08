package com.jsp.clinkNBuy.service;

import java.util.Locale.Category;

import com.jsp.clinkNBuy.dto.ResponseDto;

public interface AdminService {

	ResponseDto addCategory(Category category);

	ResponseDto viewCategories();

	ResponseDto deleteCategory(Long id);

	ResponseDto updateCategory(Long id, Category category);
	
	

}
