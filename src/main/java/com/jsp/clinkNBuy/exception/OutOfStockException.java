package com.jsp.clinkNBuy.exception;

import com.example.E_commercesite.entity.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutOfStockException extends Exception {
	String message="Out of Stock";

}
