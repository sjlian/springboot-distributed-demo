package com.lian.web;

import com.lian.common.util.VerifyCodeUtil;
import com.lian.domain.entity.UserEntity;
import com.lian.common.response.MyException;
import com.lian.common.response.RestInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 *
 * @Author lian
 * @Date 2018/3/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/unAuth")
    public RestInfo unAuth() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return new RestInfo<>(map);
    }

    @PostMapping(value = "/login")
    public RestInfo login(@RequestBody UserEntity userEntity) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword());
        Map<String, Object> result = new HashMap<>(8);
        try {
            subject.login(token);
            result.put("token", subject.getSession().getId());
            result.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            result.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            result.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            result.put("msg", "该用户不存在");
        } catch (Exception e) {
            result.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return new RestInfo<>(result);
    }

    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerifyImg")
    public void getVerifyImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串,参数1是长度，参数2是从中候选字符，可以设置成-—*zH等
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4, "1234567890");
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
