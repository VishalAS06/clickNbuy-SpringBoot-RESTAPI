package com.jsp.clinkNBuy.service;

import java.security.Principal;

import com.jsp.clinkNBuy.dto.AddProductDto;
import com.jsp.clinkNBuy.dto.ResponseDto;
import com.jsp.clinkNBuy.exception.OutOfStockException;

public interface UserService {

	ResponseDto getAllApprovedProducts(int page, int size, String sort, boolean desc);

	ResponseDto addProductToCart(AddProductDto dto, Principal principal) throws OutOfStockException;

}
