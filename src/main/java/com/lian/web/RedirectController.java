package com.lian.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 演示控制跳转
 * @Author lian
 * @Date 2018/7/5
 */
@Controller
public class RedirectController {
    @GetMapping("/")
    public String index() {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String list() {
        return "hello.html";
    }
}
