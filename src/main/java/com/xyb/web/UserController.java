package com.xyb.web;

import com.xyb.common.VerifyCodeUtil;
import com.xyb.domain.entity.UserEntity;
import com.xyb.exception.MyException;
import com.xyb.exception.RestInfo;
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
 * 演示user在内存中的操作
 * @Author lian
 * @Date 2018/3/20
 */
@RestController
@RequestMapping("/user1")
public class UserController {

    /**
     * 内存中保存，用于演示。ConcurrentHashMap是线程安全的map
     */
    private static Map<Long, UserEntity> users = new ConcurrentHashMap<>();

    @GetMapping("/list")
    public List<UserEntity> getUserList() {
        List<UserEntity> r = new ArrayList<>(users.values());
        return r;
    }

    @PostMapping(value="/add")
    public UserEntity addUser(@RequestBody @Valid UserEntity user) {
       //@RequestBody 会自动把前端传的json转换成user对象
        return user;
    }


    @GetMapping(value="/get/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @PostMapping(value="/update/{id}")
    public String updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
        UserEntity u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        return "success";
    }


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
