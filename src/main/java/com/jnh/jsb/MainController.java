package com.jnh.jsb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping()
    void index(){
        System.out.println("index");
    }

    @GetMapping("/hello")
    @ResponseBody
    void hello(){
        System.out.println("hello");
    }
}
