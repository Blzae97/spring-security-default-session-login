package com.spring.security.presentation.user;

import com.spring.security.domain.user.dto.RequestUserRegister;
import com.spring.security.domain.user.dto.UserRegisterItem;
import com.spring.security.domain.user.service.UserRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {
    private final UserRegisterService registerService;
    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public RegisterController(UserRegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * 회원가입 페이지 반환
     *
     * @return
     */
    @GetMapping(value = "/register")
    public String viewRegister() {
        return "register";
    }

    /**
     * 회원 가입 API
     *
     * @param register 회원정보
     * @return 루트 페이지 이동
     */
    @PostMapping(value = "/register")
    public String addUserRegister(RequestUserRegister register) {
        UserRegisterItem registerItem = register.toItem();
        registerService.register(registerItem);

        LOG.info("{} 회원가입 성공", registerItem.getUsername());

        return "redirect:/";
    }
}
