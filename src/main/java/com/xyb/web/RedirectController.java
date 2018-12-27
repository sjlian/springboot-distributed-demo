package com.xyb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 演示控制跳转
 * @Author lian
 * @Date 2018/7/5
 */
@Controller
public class RedirectController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/hello";
    }

    @RequestMapping("/hello")
    public String list() {
        return "hello.html";
    }
}
