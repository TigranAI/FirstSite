package ru.tigran.cardcollector.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppErrorController implements ErrorController{

    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        return "redirect:/error";
    }

}