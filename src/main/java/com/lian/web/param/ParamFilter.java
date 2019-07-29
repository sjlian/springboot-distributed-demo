package com.lian.web.param;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//@WebFilter(urlPatterns = "/api/*",filterName = "param")
@Slf4j(topic = "print-param:")
public class ParamFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) servletRequest;
        String path = r.getQueryString();
        if (path == null) {
            Map<String, String> map = new HashMap<>();
            Enumeration headerNames = ((HttpServletRequest) servletRequest).getHeaderNames();
            while (headerNames.hasMoreElements()) {//循环遍历Header中的参数，把遍历出来的参数放入Map中
                String key = (String) headerNames.nextElement();
                String value = ((HttpServletRequest) servletRequest).getHeader(key);
                map.put(key, value);
            }
            path = map.toString();
        }
        String url = r.getRequestURI();
        ParamRequestWrapper requestWrapper;
        String replaceUrl = url.replaceAll("/", "").trim();
        log.info("request url:" + url + "   & queryString:" + path);

        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
        requestWrapper = new ParamRequestWrapper((HttpServletRequest) servletRequest);
        try {
            Map map = servletRequest.getParameterMap();
            log.info("request parameter map:" + map);
            BufferedReader bufferedReader = requestWrapper.getReader();
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            log.info("request header:" + sb.toString());
        } catch (Exception e) {
            log.warn("request error:", e);
        }
        filterChain.doFilter(requestWrapper, responseWrapper);

        String result = new String(responseWrapper.getResponseData());
        servletResponse.setContentLength(-1);//解决可能在运行的过程中页面只输出一部分
        servletResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = servletResponse.getWriter();
        out.write(result);
        out.flush();
        out.close();
        log.info("response return data:" + result);
        log.info("response url:" + url + " httpStatus:" + ((HttpServletResponse) servletResponse).getStatus() + "");
    }

    @Override
    public void destroy() {

    }
}
