package com.hycun.session.boot.demo.filter;

import com.hycun.session.boot.demo.Session;
import com.hycun.session.boot.demo.adapter.RedisSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "startFilter", urlPatterns = "/*")
public class StartFilter implements Filter {
    private static Logger logger= LoggerFactory.getLogger(StartFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("my-test-filter");
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        logger.info("Source session id={}",httpServletRequest.getSession().getId());

        HttpRequestWrapper httpRequestWrapper=new HttpRequestWrapper(httpServletRequest);

        HttpSession httpSession=httpRequestWrapper.getSession();

        logger.info("New session id={}",httpSession.getId());

        filterChain.doFilter(httpRequestWrapper,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
