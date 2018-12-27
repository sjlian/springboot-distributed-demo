package com.xyb.web;

import com.xyb.common.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * 演示时间类型，分布式session，文件上传下载
 *
 * @Author lian
 * @Date 2018/3/20
 */
@RestController     // @RestController返回的是json数据，@Controller返回的是跳转控制(有模版引擎时使用)
@RequestMapping("/demo") // 方法上的@RequestMapping用于区分不同的模块
public class DemoController {

    @ApiOperation(value = "演示rest", notes = "此处表示get方法，用户的url就是/demo/getHello,得到的数据就是hello字符串")
    @GetMapping("/getHello")
    public String getHello() {
        return "hello";
    }

    @ApiOperation(value = "演示http相关", notes = "")
    @GetMapping("/setSession")
    public String setSession(String key, String value) {
        //对于参数前面没有修饰的，都是url传值，参数是可选参数，当前端不传value时，value接受到的是null
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
        assert response != null;
        response.setStatus(200);
        return "success";
    }

    @GetMapping("/getSession")
    public String getSession(String key, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //系统会自动获取requset，response，session
        return (String) session.getAttribute(key);
    }

    @ApiOperation(value = "演示传日期时间类型", notes = "演示传日期时间类型")
    @GetMapping("/time")
    public Date time(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time) {
        return time;
    }

    @PutMapping(value = "/upFile")
    public String upFile(MultipartFile[] files) {
        //TODO 文件安全校验
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString();
            try {
                file.transferTo(new File(Constant.UP_FILE_ROOT_PATH + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return "fail";
            }
        }
        return "success";
    }

    @GetMapping(value = "/downFile")
    public void downFile(HttpServletResponse resp, String fileName) {
        //设置文件名
        String acName;
        acName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        resp.setHeader("Content-Disposition", "attachment; filename=" + acName);//以附件方式下载，防止浏览器直接打开
        FileInputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(Constant.UP_FILE_ROOT_PATH + fileName);
            byte[] b = new byte[1024];
            int i;
            StringBuilder stringBuilder = new StringBuilder();
            os = resp.getOutputStream();
            while ((i = inputStream.read(b)) > 0) {
                stringBuilder.append(new String(b, 0, i));
            }
            byte[] bytes = stringBuilder.toString().getBytes();
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException ignored) {
            }
        }
    }
}
