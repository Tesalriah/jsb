package com.jnh.jsb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    String index(){
        return "redirect:/question/list";
    }
}
