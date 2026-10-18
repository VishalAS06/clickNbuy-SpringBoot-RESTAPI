package com.jsp.clinkNBuy.config;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public @interface OpenAPIDefinition {

	Info info();

	SecurityRequirement[] security();

}
