package io.herald.SpringWeb.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request, Model m)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        m.addAttribute("message","You have been logged out");

        return "loginPage";
    }
}
