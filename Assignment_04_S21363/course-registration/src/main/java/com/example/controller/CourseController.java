package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/courses")
    public String courses(Model model) {

        List<Map<String, Object>> courses =
                jdbcTemplate.queryForList("SELECT * FROM courses");

        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/register/{id}")
    public String register(@PathVariable int id, HttpSession session) {

        Map<String, Object> student =
                (Map<String, Object>) session.getAttribute("student");

        if (student == null) {
            return "login";
        }

        int studentId = (int) student.get("student_id");

        List<Map<String, Object>> check =
                jdbcTemplate.queryForList(
                        "SELECT * FROM registrations WHERE student_id=? AND course_id=?",
                        studentId, id);

        if (check.isEmpty()) {
            jdbcTemplate.update(
                    "INSERT INTO registrations(student_id, course_id) VALUES (?,?)",
                    studentId, id);
        }

        return "success";
    }
}