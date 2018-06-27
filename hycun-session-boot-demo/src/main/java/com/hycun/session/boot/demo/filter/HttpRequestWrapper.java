package com.hycun.session.boot.demo.filter;

import com.hycun.session.boot.demo.SessionRepository;
import com.hycun.session.boot.demo.adapter.HttpSessionAdapter;
import com.hycun.session.boot.demo.Session;
import com.hycun.session.boot.demo.redis.RedisSessionRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class HttpRequestWrapper<S extends Session> extends HttpServletRequestWrapper {
    private static final String CURRENT_SESSION="http_session_current";
    private SessionRepository<S> sessionRepository;

    public HttpRequestWrapper(HttpServletRequest request) {
        super(request);
        sessionRepository=new RedisSessionRepository();
    }

    public HttpSession getSession() {

        //读取本地session
        HttpSessionWrapper currentSession = getCurrentSession();
        if(currentSession!=null){
            return currentSession;
        }

        //读取RedisSession
        S reqeustSession=getReqeustSession();

        if(reqeustSession!=null){
            currentSession=new HttpSessionWrapper(reqeustSession,getServletContext());
            return currentSession;
        }

        //创建并保存session
        S session=sessionRepository.createSession();
        currentSession=new HttpSessionWrapper(session,getServletContext());
        setCurrentSession(currentSession);

        return currentSession;
    }

    private HttpSessionWrapper getCurrentSession(){
        return (HttpSessionWrapper)getAttribute(CURRENT_SESSION);
    }

    private void setCurrentSession(HttpSessionWrapper session){
        setAttribute(CURRENT_SESSION,session);
    }

    private S getReqeustSession(){
        return this.sessionRepository.findById("");
    }


    private final class HttpSessionWrapper extends HttpSessionAdapter<S>{

        public HttpSessionWrapper(S session, ServletContext servletContext) {
            super(session,servletContext);
        }
    }


}
