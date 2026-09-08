package com.jsp.clinkNBuy.exception;

import com.example.E_commercesite.entity.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountNotVerifiedException extends RuntimeException {
	private String message;
}
