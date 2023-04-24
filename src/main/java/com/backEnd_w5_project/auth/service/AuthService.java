package com.backEnd_w5_project.auth.service;

import com.backEnd_w5_project.auth.payload.LoginDto;
import com.backEnd_w5_project.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
    RegisterDto creaUtenteFake();
}
