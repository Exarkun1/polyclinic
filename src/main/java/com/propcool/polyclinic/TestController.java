package com.propcool.polyclinic;

import com.propcool.polyclinic.utils.security.UserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Тестовый контроллер (будет удалён)
 * */
@Controller
public class TestController {
    @GetMapping("/test")
    public String test(Model model, HttpSession session) {
        Integer count = (Integer) session.getAttribute("count");
        if(count == null) {
            count = 0;
        }
        session.setAttribute("count", count+1);
        model.addAttribute("count", count);
        UserDetails auth = (UserDetails) session.getAttribute("user_details");
        model.addAttribute("auth", auth);
        model.addAttribute("first", "Hello");
        model.addAttribute("second", "World");
        return "test";
    }
}
