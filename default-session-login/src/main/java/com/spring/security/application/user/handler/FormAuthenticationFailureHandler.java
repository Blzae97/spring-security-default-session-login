package com.spring.security.application.user.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String defaultErrorMessage = "Invalid Username or Password";

        if(exception instanceof BadCredentialsException){
            defaultErrorMessage = "Invalid Username or Password";
        }else if(exception instanceof UsernameNotFoundException){
            defaultErrorMessage = "User not exist";
        }else if(exception instanceof CredentialsExpiredException){
            defaultErrorMessage = "Expired password";
        }

        super.setDefaultFailureUrl("/login?error=true&exception=" + defaultErrorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
