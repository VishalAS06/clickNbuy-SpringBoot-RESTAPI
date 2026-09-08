package com.jsp.clinkNBuy.exception;

import com.example.E_commercesite.entity.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TooManyRequestsException extends RuntimeException {
	private String message="Too Many Requests";

}
