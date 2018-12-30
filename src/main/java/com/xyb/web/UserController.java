package com.xyb.web;

import com.xyb.common.util.VerifyCodeUtil;
import com.xyb.domain.entity.UserEntity;
import com.xyb.response.MyException;
import com.xyb.response.RestInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 脱离数据库演示user在内存中的操作
 * @Author lian
 * @Date 2018/3/20
 */
@RestController
@RequestMapping("/user1")
public class UserController {

    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerifyImg")
    public void getVerifyImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串,参数1是长度，参数2是从中候选字符，可以设置成-—*zH等
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4,"1234567890");
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verCode");
        //存入会话session
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 30;
        VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    @ApiOperation("校验验证码")
    @GetMapping("/validVerifyImg")
    public RestInfo validVerifyImg(HttpServletRequest request, @RequestParam String code) {
        //存入会话session
        HttpSession session = request.getSession();
        if (!code.equals(session.getAttribute("verCode"))) {
            throw MyException.notAllowed(null, "验证码错误");
        }
        //验证成功后移除
        session.removeAttribute("verCode");
        return new RestInfo();
    }
}
