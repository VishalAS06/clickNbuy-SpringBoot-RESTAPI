package com.jsp.clinkNBuy.service;




import java.security.Principal;

import com.jsp.clinkNBuy.dto.AddressDto;
import com.jsp.clinkNBuy.dto.CartItemDto;

import com.jsp.clinkNBuy.dto.ResponseDto;
import com.jsp.clinkNBuy.exception.OutOfStockException;

public interface UserService {

	ResponseDto getAllApprovedProducts(int page, int size, String sort, boolean desc);

	ResponseDto addProductToCart(CartItemDto dto, Principal principal) throws OutOfStockException;

	ResponseDto removeFromCart(CartItemDto dto, Principal principal);

	ResponseDto viewCart(Principal principal);

	ResponseDto addProductToCart1(CartItemDto dto, Principal principal) throws OutOfStockException;

	ResponseDto createOrder(AddressDto dto, Principal principal);

	ResponseDto confirmOrder(String razorpay_order_id, String razorpay_payment_id);

	ResponseDto fetchAllOrders(Principal principal);

}
