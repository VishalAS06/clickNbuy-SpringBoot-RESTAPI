package com.jsp.clinkNBuy.service;

import java.util.Locale.Category;

import com.jsp.clinkNBuy.dto.ResponseDto;

public interface AdminService {

	ResponseDto addCategory(com.jsp.clinkNBuy.entity.Category category);

	ResponseDto viewCategories();

	ResponseDto deleteCategory(Long id);

	ResponseDto updateCategory(Long id, com.jsp.clinkNBuy.entity.Category category);

	ResponseDto getProducts(int page, int size, String sort, boolean desc);

	ResponseDto changeProductStatus(Long id);
	
	

}
