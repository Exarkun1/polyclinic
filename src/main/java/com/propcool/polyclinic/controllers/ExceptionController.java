package com.propcool.polyclinic.controllers;

import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.utils.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExceptionController implements ErrorController {
    @GetMapping("/error")
    public String error(Model model) {
        UserDetails details = securityUtil.getDetails();
        if(details != null) {
            model.addAttribute("role", details.role().name().toLowerCase());
        }
        return "error/error_page";
    }

    private final SecurityUtil securityUtil;
}
