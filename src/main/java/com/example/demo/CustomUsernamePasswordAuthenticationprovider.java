package com.example.demo;

import com.example.demo.com.dao.UsersDao;
import com.example.demo.com.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class CustomUsernamePasswordAuthenticationprovider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersDao usersDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        String usernameDB = "user";
        String passwordDB = "123456";

        Users byUsername = usersDao.findByUsername(username);
        boolean ispass = byUsername.getPassword().equals(password);

        System.out.println(password+"????");
        if(ispass){
            return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
        }else {
            throw new BadCredentialsException("invalid username / password");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
