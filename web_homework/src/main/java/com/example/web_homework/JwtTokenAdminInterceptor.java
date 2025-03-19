package com.example.web_homework;


import com.example.web_homework.Classes.CustomException;
import com.example.web_homework.Classes.Response;
import com.example.web_homework.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.example.web_homework.Classes.CustomException;
import com.example.web_homework.Classes.Response;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，true放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取url
        String url=req.getRequestURI().toString();
        log.info("请求的url:{}",url);

        //2.判断请求url是否包含login，如果包含说明是登陆操作，放行
        if(url.contains("login"))
        {
            log.info("登陆操作，放行");
            return true;
        }

        if(url.contains("project"))
        {
            log.info("swagger操作，放行");
            return true;
        }
        if (req.getRequestURI().startsWith("/doc.html") || req.getRequestURI().startsWith("/webjars/") || req.getRequestURI().startsWith("/v3/api-docs")) {
            return true;
        }

        if(req.getMethod().equals("OPTIONS"))
        {
            log.info("预检操作，放行");
            return true;
        }



        // 获取请求头中的令牌
        String header = req.getHeader("Authorization");

        String jwt;
        try {
            jwt = header.substring("Bearer ".length());
        } catch (Exception e) {
            throw new CustomException(500,"token令牌为空");
        }



        //4.判断令牌是否存在，如果不存在返回未登录
        if(!StringUtils.hasLength(jwt))
        {
            log.info("请求头token为空，未登录");
            Response<String> error=new Response<String>();
            error=error.construct("not login",1,null);
            ObjectMapper objectMapper = new ObjectMapper();
            String rep = objectMapper.writeValueAsString(error);
            resp.getWriter().write(rep);
            return false;
        }

        //5.解析token，如果成功则顺带解析出令牌负载,如果解析失败返回未登录
        try{
            //解析token
            Claims claims= JwtUtils.parseJWT(jwt);
            //将userId保存
            req.setAttribute("userId",claims.get("userId",Long.class));
        }catch(Exception e){
            e.printStackTrace();
            log.info("令牌解析失败，未登录");
            Response<String> error=new Response<String>();
            error=error.construct("not login",1,null);
            ObjectMapper objectMapper = new ObjectMapper();
            String rep = objectMapper.writeValueAsString(error);
            resp.getWriter().write(rep);
            return false;
        }


        log.info("令牌合法，放行");
        return true;
    }

    /*@Override//目标资源方法后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }*/
}
