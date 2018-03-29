package com.xyb.web;

import com.xyb.exception.MyException;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @Author lian
 * @Date 2018/3/20
 */
@RestController     // @RestController返回的是json数据，@Controller返回的是跳转控制(有模版引擎时使用)
@RequestMapping("/demo") // 方法上的@RequestMapping用于区分不同的模块
public class DemoController {

    @ApiOperation(value="演示rest", notes="此处表示get方法，用户的url就是/demo/getHello,得到的数据就是hello字符串")
    @GetMapping("/getHello")
    public String getHello(){
        return "hello";
    }

    @ApiOperation(value="演示http相关", notes="")
    @GetMapping("/setSession")
    public String setSession(String key,String value){
        //对于参数前面没有修饰的，都是url传值，参数是可选参数，当前端不传value时，value接受到的是null
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        HttpSession session = request.getSession();
        session.setAttribute(key,value);
        response.setStatus(200);
        return "success";
    }

    @GetMapping("/getSession")
    public String getSession(String key,HttpServletRequest request){
        //request既可以直接通过写参数获取，也可以调用系统提供的获取,这里前端不需要传参数也会获取request，同理response
        HttpSession session = request.getSession();
        return (String) session.getAttribute(key);
    }

    @ApiOperation(value="演示传日期时间类型", notes="演示传日期时间类型")
    @GetMapping("/time")
    public Date time(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time){
        return time;
    }

}
