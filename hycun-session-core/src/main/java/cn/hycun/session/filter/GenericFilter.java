package cn.hycun.session.filter;

import javax.servlet.*;
import java.io.IOException;

public class GenericFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化配置
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //启动
    }

    @Override
    public void destroy() {

    }
}
