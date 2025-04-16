package it.aulab.xjava.services;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import it.aulab.xjava.dtos.UserDto;
import it.aulab.xjava.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void saveUser(UserDto userdto, RedirectAttributes redirectAttributes, HttpServletRequest request,HttpServletResponse response);
    User findUserByEmail(String email); 
    
}

