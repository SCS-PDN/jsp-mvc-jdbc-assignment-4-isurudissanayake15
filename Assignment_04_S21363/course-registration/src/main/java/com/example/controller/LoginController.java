package com.example.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        String sql = "SELECT * FROM students WHERE email=? AND password=?";
        List<Map<String,Object>> user =
                jdbcTemplate.queryForList(sql, email, password);

        if (!user.isEmpty()) {
            session.setAttribute("student", user.get(0));
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}