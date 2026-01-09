package com.jsp.clinkNBuy.service;

import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.jsp.clinkNBuy.dto.LoginDto;
import com.jsp.clinkNBuy.dto.OtpDto;
import com.jsp.clinkNBuy.dto.PasswordDto;
import com.jsp.clinkNBuy.dto.ResponseDto;
import com.jsp.clinkNBuy.dto.UserDto;

import jakarta.validation.Valid;
@Service
public interface AuthService {

	ResponseDto register(UserDto userDto);

	ResponseDto verifyOtp(OtpDto otpDto) throws TimeoutException;

	ResponseDto resendOtp(String email);

	ResponseDto forgetPassword(String email);

	ResponseDto forgetPassword(@Valid PasswordDto passwordDto);

	ResponseDto login(@Valid LoginDto loginDto);

	ResponseDto forgotPassword(PasswordDto passwordDto) throws TimeoutException;

	

}
